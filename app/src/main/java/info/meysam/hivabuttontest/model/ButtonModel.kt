package info.meysam.hivabuttontest.model

import info.meysam.hivaadapter.ItemBinder
import info.meysam.hivaadapter.ItemHolder
import info.meysam.hivabuttontest.R
import kotlinx.android.synthetic.main.item_button.view.*

/**
 *
 */
class ButtonModel(title: String) : ItemBinder {
    /**
     *
     */
    var title: String = ""

    /**
     *
     */
    var isSelected: Boolean = false

    override fun getResourceId(): Int {
        return R.layout.item_button
    }

    override fun bindToHolder(binder: ItemHolder, listener: Any) {
        binder.itemView.buttonOk.setTitle(title)
        binder.itemView.buttonOk.on = isSelected
        binder.itemView.buttonOk
            .setOnToggleListener { button ->
                isSelected = button.on
            }
    }

    init {
        this.title = title
    }
}