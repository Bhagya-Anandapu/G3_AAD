package com.example.fashionflair.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fashionflair.databinding.FooterViewBinding
import com.example.fashionflair.databinding.HomeOfferBannerItemBinding
import com.example.fashionflair.databinding.HomeProductListViewBinding
import com.example.fashionflair.domain.model.HomeSectionModel
import com.example.fashionflair.domain.model.HomeSectionType
import com.example.fashionflair.presentation.fragment.OnProductClicked
import com.example.fashionflair.presentation.viewHolders.FooterViewHolder
import com.example.fashionflair.presentation.viewHolders.OfferBannerViewHolder
import com.example.fashionflair.presentation.viewHolders.ProductItemViewHolder

class HomeParentAdapter(
    private val dataList: List<HomeSectionModel>,
    val listener: OnProductClicked
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return dataList[position].sectionType.ordinal
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {

            HomeSectionType.OFFER_BANNER.ordinal -> {
                val binding = HomeOfferBannerItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                OfferBannerViewHolder(binding)
            }

            HomeSectionType.PRODUCT_LIST.ordinal -> {
                val binding = HomeProductListViewBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )

                ProductItemViewHolder(binding, listener)
            }

            HomeSectionType.FOOTER.ordinal -> {
                val binding =
                    FooterViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                FooterViewHolder(binding)
            }

            else -> {
                val binding = HomeOfferBannerItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                OfferBannerViewHolder(binding)
            }
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (dataList[position].sectionType) {
            HomeSectionType.OFFER_BANNER -> {
                (holder as OfferBannerViewHolder).setData(dataList[position].bannerText ?: "")
            }

            HomeSectionType.PRODUCT_LIST -> {
                (holder as ProductItemViewHolder).setData(
                    dataList[position].productList ?: emptyList()
                )
            }

            HomeSectionType.FOOTER -> {
                (holder as FooterViewHolder).setData()
            }
        }
    }
}