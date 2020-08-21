package com.example.navigationcomponent

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.navigationcomponent.model.Money
import com.example.navigationcomponent.util.showToast
import kotlinx.android.synthetic.main.fragment_specify_amount.*;
import kotlinx.android.synthetic.main.fragment_specify_amount.cancel_btn
import java.math.MathContext

class SpecifyAmountFragment : Fragment(), View.OnClickListener {

    private lateinit var navController: NavController
    private lateinit var recipient: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recipient = requireArguments().getString("recipient").toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_specify_amount, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        send_btn.setOnClickListener(this)
        cancel_btn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {

            R.id.send_btn -> {
                if (!TextUtils.isEmpty(input_amount.text.toString())) {
                    val bundle = bundleOf(
                        "recipient" to recipient,
                         "amount" to Money(input_amount.text.toString().toBigDecimal(MathContext.DECIMAL128))
                    )
                    navController.navigate(R.id.action_specifyAmountFragment_to_confirmationFragment, bundle)
                } else {
                    activity?.showToast("Enter Recipient!!!")
                }
            }

            R.id.cancel_btn -> {
                requireActivity().onBackPressed()
            }
        }
    }
}