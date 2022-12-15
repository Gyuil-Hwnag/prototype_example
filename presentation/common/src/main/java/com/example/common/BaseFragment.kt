package com.example.common

import android.app.Application
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDirections
import androidx.navigation.fragment.DialogFragmentNavigator
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

abstract class BaseFragment<T : ViewDataBinding, R : BaseViewModel>(layoutId: Int) : Fragment(layoutId) {

    private var _binding: T? = null
    val binding get()= requireNotNull(_binding)

    /**
     * setContentView로 호출할 Layout의 리소스 Id.
     * ex) R.layout.activity_main
     */
    abstract val layoutResourceId: Int

    /**
     * viewModel 로 쓰일 변수.
     */
    abstract val viewModel: R

    /**
     * 두번째로 호출.
     * 데이터 바인딩 및 Coroutine 설정.
     * ex) lifecyelScope.launch{}, lifecycleScope.launchWhenStarted{] ..
     */
    abstract fun initStartView()

    /**
     * 두번째로 호출.
     * 데이터 바인딩 및 rxjava 설정.
     * ex) rxjava observe, databinding observe..
     */
    abstract fun initDataBinding()

    /**
     * 바인딩 이후에 할 일을 여기에 구현.
     * 그 외에 설정할 것이 있으면 이곳에서 설정.
     * 클릭 리스너도 이곳에서 설정.
     */
    abstract fun initAfterBinding()

    private var isSetBackButtonValid = false

    /**
     * Exception을 처리할 SharedFlow
    */
    protected var exception: SharedFlow<Throwable>? = null
    private var toast: Toast? = null

    init {
        lifecycleScope.launchWhenStarted {
            launch {
                exception?.collect { exception ->
                    showToastMessage(exception)
                }
            }

            launch {
                viewModel.errorEvent.collect { e ->
                    showToastMessage(e)
                    Log.e("DIDA", "onStart: ${e}")
                }
            }
        }
    }

    // 코틀린의 전역변수 문법
    companion object {
        // 만들어져있는 SharedPreferences 를 사용해야합니다. 재생성하지 않도록 유념해주세요
        lateinit var sSharedPreferences: SharedPreferences
        lateinit var editor: SharedPreferences.Editor

        val email = "email"
        val password = "password"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sSharedPreferences = requireContext().getSharedPreferences("Prototype", Application.MODE_PRIVATE)
        editor = sSharedPreferences.edit()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, layoutResourceId, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        initStartView()
        initDataBinding()
        initAfterBinding()
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        toast?.cancel()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // Toast Message 관련 함수
    protected fun showToastMessage(e: Throwable?) {
        toast?.cancel()
        toast = Toast.makeText(activity, e?.message, Toast.LENGTH_SHORT)?.apply { show() }
    }

    // Toast Message 관련 함수
    protected fun toastMessage(message: String) {
        toast?.cancel()
        toast = Toast.makeText(activity, message, Toast.LENGTH_SHORT)?.apply { show() }
    }

    // navigation 중복체크 관리 <- checkNavigation 대신 사용할것
    protected fun Fragment.navigate(directions: NavDirections) {
        val controller = findNavController()
        val currentDestination = (controller.currentDestination as? FragmentNavigator.Destination)?.className
            ?: (controller.currentDestination as? DialogFragmentNavigator.Destination)?.className
        if (currentDestination == this.javaClass.name) {
            controller.navigate(directions)
        }
    }
}

