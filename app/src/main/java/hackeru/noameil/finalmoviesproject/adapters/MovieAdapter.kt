package hackeru.noameil.finalmoviesproject.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import hackeru.noameil.finalmoviesproject.data.models.Movie
import hackeru.noameil.finalmoviesproject.databinding.MovieItemBinding

class MovieAdapter(private val movies: List<Movie>) : RecyclerView.Adapter<MovieAdapter.VH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VH(
            MovieItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun getItemCount() = movies.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        val movie = movies[position]
        with(holder.binding) {
            textTitle.text = movie.title
            Picasso.get()
                .load(movie.posterUrl)
                .into(imagePoster)
        }
    }

    class VH(val binding: MovieItemBinding) : RecyclerView.ViewHolder(binding.root)
}