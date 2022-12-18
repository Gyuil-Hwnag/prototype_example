package com.example.community

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.commnunity.R
import com.example.commnunity.databinding.FragmentCommunityBinding
import com.example.common.BaseFragment
import com.example.model.Community
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class CommunityFragment : BaseFragment<FragmentCommunityBinding, CommunityViewModel>(R.layout.fragment_community) {

    private val TAG = "CommunityFragment"

    override val layoutResourceId: Int
        get() = R.layout.fragment_community

    override val viewModel : CommunityViewModel by viewModels()
    private val communityAdapter by lazy { CommunityAdapter(viewModel) }


    override fun initStartView() {
        binding.apply {
            this.viewmodel = viewModel
            this.lifecycleOwner = viewLifecycleOwner
        }
        exception = viewModel.errorEvent
        initAdapter()
    }

    override fun initDataBinding() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.navigate.collectLatest {
                navigate(CommunityFragmentDirections.actionCommunityFragmentToCommunityDetailFragment())
            }
        }
    }

    override fun initAfterBinding() {
    }

    private fun initAdapter() {
        val community1 = Community(
            communityId = 1,
            userName = "[TEAM] 나이스연합",
            contents = "학생작품, 조형물, 키네틱아트, 기구, 발명품 등 고객님이 의뢰하신 제품을 만들어 드립니다. 간단한 가공이나 작은 아이디어라도 언제든 의뢰해주시면 성심성의껏 답변해드리겠습니다.",
            dateTime = "2022-12-22"
        )
        val community2 = Community(
            communityId = 2,
            userName = "[TEAM] 에코랩",
            contents = "사전 연락을 통해 예약 후 방문하시면 자세하게 상담해드리겠습니다.\n" +
                    "방문 전에 제작에 관한 내용을 제작의뢰 접수 또는 메일을 통해 공유해주시면 더 원활한 상담이 가능합니다.\n" +
                    "\n" +
                    "* 방문 예약 관련 문의는 고객센터(0507-1310-7881)로 연락주시면 상담을 통해 예약을 도와드립니다.",
            dateTime = "2022-12-22"
        )
        val community3 = Community(
            communityId = 3,
            userName = "[TEAM] 나일권",
            contents = "고객님이 희망하는 납품일을 기준으로 하고 있으나, 상담 및 제작 과정 중 협의를 통해 납품일이 조정될 수 있습니다.",
            dateTime = "2022-12-21"
        )
        val community4 = Community(
            communityId = 4,
            userName = "임다",
            contents = "키워드 다섯개로 부족합니다.. 다 눌러주고 싶을 정도로 친절하셨고 하나하나 체험 해주시면서 위험한 불은 직접 도와주시고 어떤 방식으로 만들어지는지 하나하나 직접 보여주시면서 따라하기도 편하고 이해하기도 편했습니다. 반지가 못생겨지면 어떡하지 잘 못만들어도 괜찮을까? 싶어도 워낙 잘했다고 칭찬 해주시면서 수정이 필요한 부분도 정리해주셨어요. 연인,친구 끼리 가족끼리도 와서 추억을 만들어 보는걸 추천합니다. 가격도 엄청 합리적이였습니다. 보는 내내 만족하며 이 가격에 이런 퀄리티가 나올수 있구나 싶어요. 나중에 반지 만들고 싶을때 또 다시 두시간 반이 걸려서 오고싶어요. 정말 감사합니다. 좋은 추억 만들어주셔서!\uD83D\uDE0A",
            dateTime = "2022-12-20"
        )
        val community5 = Community(
            communityId = 5,
            userName = "Johnadan",
            contents = "처음 만들어봤는데 편한 분위기로 만들 수 있는 시간이었어요!! 재밌었습니다\uD83D\uDC96",
            dateTime = "2022-12-20"
        )
        val community6 = Community(
            communityId = 6,
            userName = "황예슬",
            contents = "반지공방 세번째인데 분위기나 디자인이나 여기가 TOP..\uD83D\uDC4D 세심하게 알려주시고 완전 친절하심! 손재주 없어도 걱정 안해도 될듯용! 반지 너므 이뻐여ㅠㅜ",
            dateTime = "2022-12-20"
        )
        val community7 = Community(
            communityId = 7,
            userName = "이찬희",
            contents = "추천합니다 만들어볼수있는 종류도 다양하고 우려와달리 생각보다 단순한 형태를 고르더라도 직접 은막대에서 반지까지 알려주는대로 만들다보니, 신기하기도 하고 세상에 하나밖에 없는반지를 만드는 느낌도 드네요!! 기념일에 추천합니다",
            dateTime = "2022-12-19"
        )
        val community8 = Community(
            communityId = 8,
            userName = "쟈스민",
            contents = "선생님이 너무 친절하게 다 알려주시고 후작업 전인데도 너무 이쁘고 좋았습니다! 얼른 하루빨리 작업된 반지 받고싶어요 ㅠㅠ 감사합니다!!",
            dateTime = "2022-12-19"
        )
        val community9 = Community(
            communityId = 9,
            userName = "duru",
            contents = "여자친구 생일이여서 특별한 이벤트로 영롱달빛을 예약해서\n" +
                    "같이 만들어봤어요 처음이여서 긴장하고 왔는데 너무 친절\n" +
                    "하게 알려주시면서 만들고 재밌는 추억 만들다 가요! 내용 더보기",
            dateTime = "2022-12-15"
        )
        val community10 = Community(
            communityId = 10,
            userName = "lyun",
            contents = "분위기 너무 예쁘구요 일단 직원분들도 친절하고\n" +
                    "뭘해야할지 모르겠는데 방향도 잘 잡아주시구 만들때마다 잘한다잘한다해주셔서 자존감도 채워져버리고 계좌이체하면 예쁜팔찌도 주시구 여기직원분 MBTI가 E일것 같았지만 I인 반전도 있고 매니저님? 안경쓰시고 작업하는것도 멋있었고 여기는 홍보가 널리되어가지구 돈쭐나고 리뷰로도 혼쭐나야하거든요 결론은 반지공방은 여기로 오세요\uD83D\uDC99",
            dateTime = "2022-12-15"
        )
        val community11 = Community(
            communityId = 11,
            userName = "hgi",
            contents = "사장님도 너무 친절히 꼼꼼하게 잘 알려주시고 마지막 선물도 너무 감사드려용\uD83D\uDE2D 2번째 반지인데 여기가 더 좋은거같아요 다음에 잃어버리면 또 올께요 \uD83D\uDE0A 최고~~~\uD83D\uDC4D",
            dateTime = "2022-12-15"
        )
        val communityList = listOf(community1, community2, community3, community4, community5, community6,
            community7, community8, community9, community10, community11)

        communityAdapter.submitList(communityList)
        binding.communityRecycler.adapter = communityAdapter
    }
}
