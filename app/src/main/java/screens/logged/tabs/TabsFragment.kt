package screens.logged.tabs

import activities.logged.LoggedActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.joao.simsschool.R
import com.joao.simsschool.databinding.FragmentTabsBinding

class TabsFragment: Fragment() {
    lateinit var binding: FragmentTabsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_tabs, container, false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupTabs()
    }

    private fun setupTabs() {
        val sectionsPagerAdapter = TabsPageAdapter(requireContext(), parentFragmentManager)

        val viewPager = binding.fragmentTabsViewPager.apply {
            adapter = sectionsPagerAdapter
        }

        val tabs = binding.fragmentTabsHeader.getTabLayout()
        tabs.setupWithViewPager(viewPager)
    }


}