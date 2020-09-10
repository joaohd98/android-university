package screens.logged.tabs.tips.modal_medias

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.joao.simsschool.R
import com.joao.simsschool.databinding.ModalMediasTipsBinding
import repositories.tips.TipsResponse
import utils.CubeTransformer
import utils.CustomRoundBottomSheet

class MediasModal(
    private val tips: ArrayList<TipsResponse>,
    private val index: Int
): CustomRoundBottomSheet() {
    lateinit var binding: ModalMediasTipsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.modal_medias_tips, container, false
        )

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        setFullScreen()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = MediasAdapter(tips, context as FragmentActivity)

        binding.modalMediasViewPager.apply {
            this.adapter = adapter
            setCurrentItem(index, false)
            setPageTransformer(CubeTransformer())
        }
    }

    companion object {
        fun invoke(
            fragmentManager: FragmentManager,
            tips: ArrayList<TipsResponse>,
            index: Int
        ) {
            val bottomSheetFragment = MediasModal(tips, index)
            bottomSheetFragment.show(fragmentManager, bottomSheetFragment.tag)
        }
    }
}