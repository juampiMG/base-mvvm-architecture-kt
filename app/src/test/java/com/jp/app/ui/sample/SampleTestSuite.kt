package com.jp.app.ui.sample

import com.jp.app.ui.sample.view.SampleViewTest
import com.jp.app.ui.sample.viewModel.SampleViewModelTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(SampleViewModelTest::class, SampleViewTest::class)
class SampleTestSuite
