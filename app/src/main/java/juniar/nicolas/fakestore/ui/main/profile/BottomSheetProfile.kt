package juniar.nicolas.fakestore.ui.main.profile

import android.content.Context
import com.google.android.material.bottomsheet.BottomSheetDialog
import juniar.nicolas.fakestore.data.model.UserModel
import juniar.nicolas.fakestore.databinding.BottomSheetProfileBinding

class BottomSheetProfile(
    context: Context,
    userModel: UserModel,
    logoutAction: () -> Unit
) : BottomSheetDialog(context) {
    val binding: BottomSheetProfileBinding = BottomSheetProfileBinding.inflate(layoutInflater)

    init {
        setContentView(binding.root)
        with(binding) {
            tvFullname.text = userModel.name?.getFullname()
            tvUsername.text = userModel.username
            tvEmail.text = userModel.email
            tvAddress.text = userModel.address?.getFullAddress()
            tvPhoneNumber.text = userModel.phone
            btnLogout.setOnClickListener {
                logoutAction.invoke()
                dismiss()
            }
        }
    }
}