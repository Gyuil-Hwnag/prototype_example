package com.example.home

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.common.BaseFragment
import com.example.home.databinding.FragmentHomeBinding
import com.example.model.Category
import com.example.model.HotItem
import com.example.model.Recommended
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(R.layout.fragment_home) {

    private val TAG = "HomeFragment"

    override val layoutResourceId: Int
        get() = R.layout.fragment_home

    override val viewModel : HomeViewModel by viewModels()
    private val categoryAdapter by lazy { CategoryAdapter(viewModel) }
    private val recommededAdapter by lazy { RecommededAdapter(viewModel) }
    private val hotAdapter by lazy { HotItemAdapter(viewModel) }

    override fun initStartView() {
        binding.apply {
            this.viewmodel = viewModel
            this.lifecycleOwner = viewLifecycleOwner
        }
        exception = viewModel.errorEvent
        initCategory()
        initRecommended()
        initHotItem()
    }

    override fun initDataBinding() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.navigate.collectLatest {
                when(it) {
                    is HomeNavigationAction.NavigateToCategoryDetail -> {}
                    is HomeNavigationAction.NavigateToDetail -> navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment())
                }
            }
        }
    }

    override fun initAfterBinding() {
    }

    private fun initCategory() {
        val category1 = Category(
            categoryId = 1,
            categoryName = "키링",
            categoryImg = R.drawable.key)
        val category2 = Category(
            categoryId = 2,
            categoryName = "문구",
            categoryImg = R.drawable.mungu)
        val category3 = Category(
            categoryId = 3,
            categoryName = "모바일",
            categoryImg = R.drawable.mobile)
        val category4 = Category(
            categoryId = 4,
            categoryName = "텀블러/컵",
            categoryImg = R.drawable.cup
        )
        val category5 = Category(
            categoryId = 5,
            categoryName = "생활잡화",
            categoryImg = R.drawable.life
        )
        val category6 = Category(
            categoryId = 6,
            categoryName = "뱃지/명찰",
            categoryImg = R.drawable.batge
        )
        val category7 = Category(
            categoryId = 7,
            categoryName = "가방/파우치",
            categoryImg = R.drawable.bag
        )
        val category8 = Category(
            categoryId = 8,
            categoryName = "인형",
            categoryImg = R.drawable.dollar
        )
        val category9 = Category(
            categoryId = 9,
            categoryName = "디지털/IT",
            categoryImg = R.drawable.digiter
        )
        val category10 = Category(
            categoryId = 10,
            categoryName = "기타",
            categoryImg = R.drawable.another
        )
        val categoryList = listOf(category1, category2, category3, category4,
            category5, category6, category7, category8, category9, category10)
        categoryAdapter.submitList(categoryList)

        val flexLayoutManager = FlexboxLayoutManager(requireContext()).apply {
            this.flexDirection = FlexDirection.ROW
            this.justifyContent = JustifyContent.CENTER
            this.alignItems = AlignItems.CENTER
        }
        binding.categoryRecycler.apply {
            this.adapter = categoryAdapter
            this.layoutManager = flexLayoutManager
        }
    }

    private fun initRecommended() {
        val recommended1 = Recommended(
            recommendId = 1,
            recommendImg = R.drawable.children_paint,
            recommendName = "아이들과 함께하는 제작",
            teamName = "[TEAM] 메이커스",
            contents = "아이들과 디자인 부터 제작까지 함께하는 케릭터 인형 만들기"
        )
        val recommended2 = Recommended(
            recommendId = 1,
            recommendImg = R.drawable.adult_paint,
            recommendName = "처음 그림을 접하는 성인을 위한 그림 기초반",
            teamName = "[TEAM] 어른이들",
            contents = "처음 그림을 접해보는 성인을 위한 그림 기초반"
        )
        val recommended3 = Recommended(
            recommendId = 1,
            recommendImg = R.drawable.cup,
            recommendName = "직접 컵 만들어 보기[기초반]",
            teamName = "[TEAM] 공작 제작소",
            contents = "직접 컵을 만들어 보면서 다지인 도면 제작부터 발주 까지"
        )
        val recommended4 = Recommended(
            recommendId = 1,
            recommendImg = R.drawable.minium_child_paint,
            recommendName = "유아를 위한 그림 그리기반",
            teamName = "[TEAM] 미니멀 차일드",
            contents = "3세부터 7세까지를 위한 그림 그리기반"
        )
        val recommendedList = listOf<Recommended>(recommended1, recommended2, recommended3, recommended4)
        recommededAdapter.submitList(recommendedList)

        binding.recommendedRecycler.adapter = recommededAdapter
    }

    private fun initHotItem() {
        val hot1 = HotItem(
            hotId = 1,
            hotImg = R.drawable.children_paint,
            hotName = "아이들과 함께하는 제작",
            teamName = "[TEAM] 메이커스",
            contents = "아이들과 디자인 부터 제작까지 함께하는 케릭터 인형 만들기"
        )
        val hot2 = HotItem(
            hotId = 1,
            hotImg = R.drawable.adult_paint,
            hotName = "처음 그림을 접하는 성인을 위한 그림 기초반",
            teamName = "[TEAM] 어른이들",
            contents = "처음 그림을 접해보는 성인을 위한 그림 기초반"
        )
        val hot3 = HotItem(
            hotId = 1,
            hotImg = R.drawable.cup,
            hotName = "직접 컵 만들어 보기[기초반]",
            teamName = "[TEAM] 공작 제작소",
            contents = "직접 컵을 만들어 보면서 다지인 도면 제작부터 발주 까지"
        )
        val hot4 = HotItem(
            hotId = 1,
            hotImg = R.drawable.minium_child_paint,
            hotName = "유아를 위한 그림 그리기반",
            teamName = "[TEAM] 미니멀 차일드",
            contents = "3세부터 7세까지를 위한 그림 그리기반"
        )
        val hotList = listOf<HotItem>(hot1, hot2, hot3, hot4)
        hotAdapter.submitList(hotList)

        binding.hotRecycler.adapter = hotAdapter
    }
}
