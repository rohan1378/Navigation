package com.makor.hotornot

//import android.support.v4.app.ActivityCompat
//import android.support.v4.content.ContextCompat
//import android.support.v7.app.AppCompatActivity
import android.content.Context
import android.content.res.AssetManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Picture
import android.graphics.drawable.PictureDrawable
import android.net.http.SslError
import android.webkit.SslErrorHandler
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.makor.hotornot.classifier.*
import com.makor.hotornot.classifier.tensorflow.ImageClassifierFactory
import com.makor.hotornot.utils.getCroppedBitmap

private const val REQUEST_PERMISSIONS = 1
private const val REQUEST_TAKE_PICTURE = 2


class Classif : AppCompatActivity() {





    companion object {
        /*private lateinit var context: Context
        @JvmStatic
        fun setContext(con: Context) {
            context=con
        }
           */
        /*
        private var instance: MainActivity? = null
        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
        val context: Context = applicationContext()
        */
        //val webview = WebView(context)
        private lateinit var classifier: Classifier
        //internal lateinit var bitmap: Bitmap

        @JvmStatic
        fun init(context: Context) {
            var assets: AssetManager = context.getAssets()
            classifier = ImageClassifierFactory.create(
                    assets,
                    GRAPH_FILE_PATH,
                    LABELS_FILE_PATH,
                    IMAGE_SIZE,
                    GRAPH_INPUT_NAME,
                    GRAPH_OUTPUT_NAME
            )

        }


        @JvmStatic private fun pictureDrawable2Bitmap(picture: Picture): Bitmap {
            var pd: PictureDrawable = PictureDrawable(picture)
            var bt: Bitmap = Bitmap.createBitmap(pd.getIntrinsicWidth(), pd.getIntrinsicHeight(), Bitmap.Config.ARGB_8888)
            var canvas: Canvas = Canvas(bt)
            canvas.drawPicture(pd.getPicture())
            return bt
        }


        @JvmStatic fun classifyPhoto(context: Context, bitmap: Bitmap): Result {
//            var picture: Picture = webview.capturePicture()
//            var bitmap: Bitmap = pictureDrawable2Bitmap(picture)

            val croppedBitmap = getCroppedBitmap(bitmap)
            val result = classifier.recognizeImage(croppedBitmap)
            return result
        }

    }
}
