package com.nj.framework.ktx

import android.app.Activity
import android.net.Uri
import android.os.Bundle
import android.os.CancellationSignal
import android.os.ParcelFileDescriptor
import android.print.PageRange
import android.print.PrintAttributes
import android.print.PrintDocumentAdapter
import android.print.PrintDocumentInfo
import android.print.PrintManager
import androidx.core.content.getSystemService
import java.io.FileInputStream
import java.io.FileOutputStream

fun printDocument(
    activity: Activity,
    documentName: String?,
    documentPath: String?,
    errorBlock: (String?) -> Unit
) {
    if (documentPath.isNullOrEmpty()) {
        return errorBlock.invoke("File path must not be null or blank.")
    }
    val documentUri = Uri.parse(documentPath) ?: return errorBlock.invoke("")
    printDocument(activity, documentName, documentUri, errorBlock)
}

fun printDocument(
    activity: Activity,
    documentName: String?,
    documentUri: Uri?,
    errorBlock: (String?) -> Unit
) {
    if (documentName.isNullOrEmpty() || documentUri == null) {
        return errorBlock.invoke("Document name or file URI must not be null or blank.")
    }
    val printManager = activity.getSystemService<PrintManager>()
        ?: return errorBlock.invoke("PrintManager is not available.")
    val printAdapter = object : PrintDocumentAdapter() {
        override fun onWrite(
            pages: Array<out PageRange>?,
            destination: ParcelFileDescriptor?,
            cancellationSignal: CancellationSignal?,
            callback: WriteResultCallback?
        ) {
            val fileDescriptor = activity.contentResolver.openFileDescriptor(documentUri, "r")
                ?: return errorBlock.invoke("Unable to open file descriptor for the document.")
            val inputStream = FileInputStream(fileDescriptor.fileDescriptor)
            try {
                val outputStream = FileOutputStream(destination?.fileDescriptor)
                inputStream.use { input ->
                    outputStream.use { fileOut ->
                        input.copyTo(fileOut)
                    }
                }
                callback?.onWriteFinished(arrayOf(PageRange.ALL_PAGES))
            } catch (e: Exception) {
                e.printStackTrace()
                errorBlock.invoke("Error occurred while writing document to print destination: ${e.message}")
            } finally {
                fileDescriptor.close()
            }
        }

        override fun onLayout(
            oldAttributes: PrintAttributes?,
            newAttributes: PrintAttributes?,
            cancellationSignal: CancellationSignal?,
            callback: LayoutResultCallback?,
            extras: Bundle?
        ) {
            val pdi = PrintDocumentInfo.Builder(documentName)
                .setContentType(PrintDocumentInfo.CONTENT_TYPE_DOCUMENT)
                .build()
            callback?.onLayoutFinished(pdi, true)
        }
    }
    printManager.print(documentName, printAdapter, null)
}
