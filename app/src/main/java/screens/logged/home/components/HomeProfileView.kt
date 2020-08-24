package screens.logged.home.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentManager
import com.joao.simsschool.R
import com.joao.simsschool.databinding.ViewHomeProfileBinding
import repositories.user.UserResponse
import utils.OnClickDataBinding

class HomeProfileView : ConstraintLayout {
    lateinit var binding: ViewHomeProfileBinding

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )

    init {
        if (isInEditMode) {
            LayoutInflater.from(context).inflate(R.layout.view_home_profile, this, true)
        }
        else {
            binding = ViewHomeProfileBinding.inflate(LayoutInflater.from(context), this, true)
        }
    }

    fun setChangeProfile(supportFragmentManager: FragmentManager) {
        binding.changePicture = object: OnClickDataBinding {
            override fun onClick() {
                val bottomSheetFragment = HomeChangePictureFragment()

                bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)


            }
        }
    }

    fun setUser(user: UserResponse?) {
        binding.userResponse = user
    }

}