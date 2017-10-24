package com.deakishin.hotelsapp.utils

import android.graphics.Bitmap
import com.squareup.picasso.Transformation

/** Picasso transformation that crops border of the image.
 * @param cropWidth Width of the cropped border. Must be >= 0. */
class BorderCropTransformation(private val cropWidth: Int = 1) : Transformation {

    override fun key() = "borderCrop $cropWidth"

    override fun transform(source: Bitmap?): Bitmap? {
        return source?.let {
            val crop = Math.max(cropWidth, 0)
            val result = with(it) {
                Bitmap.createBitmap(it,
                        crop, crop,
                        width - crop * 2, height - crop * 2)
            }
            if (result != source) {
                it.recycle()
            }
            result
        }
    }

}