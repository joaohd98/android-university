package screens.logged.scores

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.joao.simsschool.R
import kotlinx.android.synthetic.main.screen_scores.*
import kotlinx.android.synthetic.main.view_scores_classes.*
import repositories.RepositoryStatus
import screens.logged.scores.components.ScoresClassesAdapter
import utils.observeOnce


class ScoresScreen : Fragment() {
    private val viewModel: ScoresViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.screen_scores, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        callRequests()
        initSemesters()
        initClasses()
        setObserves()
    }

    private fun callRequests() {
        viewModel.user.observeOnce(viewLifecycleOwner) {
            if (it != null) {
                viewModel.callScores()
            }
        }
    }

    private fun setObserves() {
        val viewSemesters = view_scores_semesters
        val viewClasses = view_scores_classes

        viewModel.statusScore.observe(viewLifecycleOwner, {
            when(it) {
                RepositoryStatus.FAILED -> { }
                RepositoryStatus.LOADING -> { }
                RepositoryStatus.SUCCESS -> {
                    viewSemesters.setSuccess(viewModel.scores.size)
                    viewClasses.setSuccess()
                }
                else -> {}
            }
        })

        viewModel.actualSemester.observe(viewLifecycleOwner, {
            val binding = viewSemesters.binding
            binding.viewScoresSemestersRecyclerView.adapter!!.notifyDataSetChanged()
        })
    }

    private fun initSemesters() {
        view_scores_semesters.initRecycleView(requireContext(), viewModel.actualSemester)
    }

    private fun initClasses() {
        view_scores_classes.initRecyclerView(requireContext())
    }
}