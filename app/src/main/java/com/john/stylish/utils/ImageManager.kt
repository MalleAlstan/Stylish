package com.john.stylish.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.util.Log
import android.util.LruCache
import android.widget.ImageView
import com.john.stylish.R
import java.io.InputStream
import java.lang.ref.WeakReference
import java.net.URL
import java.nio.charset.MalformedInputException
import java.util.concurrent.Executors

object ImageManager {

    lateinit var lruCache: LruCache<String, Bitmap>
        private set

    init {
        initLruCache()
    }

    private fun initLruCache() {

        val maxMemory = (Runtime.getRuntime().maxMemory() / 1024).toInt()
        val cacheSize = maxMemory / 2

        lruCache = object : LruCache<String, Bitmap>(cacheSize) {
            override fun sizeOf(key: String, value: Bitmap): Int {
                return value.byteCount / 1024
            }
        }
    }

    fun setImageByUrl(imageView: ImageView, imageUrl: String) {

        val bitmap = lruCache.get(imageUrl)

        if (bitmap == null) {

            Log.d(Constants.TAG, "LruCache doesn't exist, start download.: $imageUrl")

            lockImagePairing(imageView, imageUrl)
            imageView.setImageResource(R.drawable.ic_placeholder)

            DownloadImageTask(WeakReference(imageView), imageUrl)
                .executeOnExecutor(Executors.newCachedThreadPool())
        } else {

            Log.d(Constants.TAG, "LruCache exist, setImageByUrl bitmap directly.: $imageUrl")
            imageView.setImageBitmap(bitmap)
        }
    }

    class DownloadImageTask(private val imageViewReference: WeakReference<ImageView>,
                            private val imageUrl: String) : AsyncTask<Any, Any, Bitmap>() {

        override fun doInBackground(objects: Array<Any>): Bitmap? {

            return decodeBitmap(imageUrl, 200)
        }

        override fun onPostExecute(bitmap: Bitmap?) {
            super.onPostExecute(bitmap)

            if (bitmap != null) {

                lruCache.put(imageUrl, bitmap)

                if (isImagePairingCorrect(imageViewReference.get(), imageUrl)) {
                    imageViewReference.get()?.setImageBitmap(bitmap)
                }
            }
        }
    }

    private fun calculateInSampleSize(options: BitmapFactory.Options, reqWidth: Int, reqHeight: Int): Int {
        // Raw height and width of image
        val height = options.outHeight
        val width = options.outWidth
        var inSampleSize = 1

        if (height > reqHeight || width > reqWidth) {

            val halfHeight = height / 2
            val halfWidth = width / 2

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while (halfHeight / inSampleSize > reqHeight && halfWidth / inSampleSize > reqWidth) {
                inSampleSize *= 2
            }
        }
        return inSampleSize
    }

    fun decodeBitmap(url: String, maxWidth: Int): Bitmap? {

        var bitmap: Bitmap? = null
        try {
            val options = BitmapFactory.Options()
            options.inPreferredConfig = Bitmap.Config.RGB_565
            options.inSampleSize = calculateInSampleSize(options, maxWidth, maxWidth)

            val `is` = URL(url).content as InputStream
            bitmap = BitmapFactory.decodeStream(`is`, null, options)
        } catch (e: MalformedInputException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return bitmap
    }

    private fun lockImagePairing(imageView: ImageView, url: String) {
        imageView.tag = url
    }

    private fun isImagePairingCorrect(imageView: ImageView?, url: String): Boolean {
        return if (imageView != null) imageView.tag == url else false
    }
}