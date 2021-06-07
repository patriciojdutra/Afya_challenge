package br.com.dutra.patricio.tvmaze.util

import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import br.com.dutra.patricio.tvmaze.R

class BiometricUtil {

    private lateinit var biometricPrompt: BiometricPrompt

    fun startBiometricPrompt(act: AppCompatActivity, success: () -> Unit) {
        val executor = ContextCompat.getMainExecutor(act)

        val callback = object: BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                Toast.makeText(act,"$errorCode :: $errString",Toast.LENGTH_LONG).show()
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                Toast.makeText(act, act.getString(R.string.authentication_failed),Toast.LENGTH_LONG).show()
            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                success()
            }
        }
        biometricPrompt = BiometricPrompt(act,executor,callback)
    }

    fun authenticate(context: Context){
        val biometricManager = BiometricManager.from(context)
        if (biometricManager.canAuthenticate() == BiometricManager.BIOMETRIC_SUCCESS){
            biometricPrompt.authenticate(getPromptInfo(context))
        }
    }

    private fun getPromptInfo(context: Context): BiometricPrompt.PromptInfo {
        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle(context.getString(R.string.title_dialog_biometric))
            .setSubtitle(context.getString(R.string.insert_digital))
            .setDescription(context.getString(R.string.description_dialog_biometric))
            .setDeviceCredentialAllowed(true)
            .build()
        return promptInfo
    }

}