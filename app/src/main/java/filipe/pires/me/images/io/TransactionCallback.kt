package filipe.pires.me.images.io

import filipe.pires.me.images.io.interactors.InteractorResponse


interface TransactionCallback {
    fun onSuccess(response: InteractorResponse)
}