package layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.orwma_lv7.Product
import com.example.orwma_lv7.R


class MakeupBrandRecyclerAdapter(private val items: List<Product>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return BrandViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.product_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is BrandViewHolder -> {
                holder.bind(this.items[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }


    class BrandViewHolder constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private val photoImage: ImageView =
            itemView.findViewById(R.id.productImageView)
        private val name: TextView =
            itemView.findViewById(R.id.nameTextView)
        private val price: TextView =
            itemView.findViewById(R.id.priceTextView)
        private val rating: TextView =
            itemView.findViewById(R.id.ratingTextView)
        private val description: TextView =
            itemView.findViewById(R.id.descriptionTextView)

        fun bind(product: Product) {
            Glide
                .with(itemView.context)
                .load(product.image_link)
                .into(photoImage)
            name.text = product.name
            price.text = product.price
            rating.text = product.rating
            description.text = product.description
        }
    }
}
