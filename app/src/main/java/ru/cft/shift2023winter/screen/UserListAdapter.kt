package ru.cft.shift2023winter.screen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ru.cft.shift2023winter.R
import ru.cft.shift2023winter.domain.entity.User

class UserListAdapter(private val userClickListener: (User) -> Unit) : RecyclerView.Adapter<HistoryViewHolder>() {

    var users: List<User> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.user_list_item, parent, false)
        return HistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(users[position], userClickListener)
    }

    override fun getItemCount(): Int =
        users.size
}

class HistoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val userFullNameText get() = itemView.findViewById<TextView>(R.id.userFullNameText)
    private val userEmailText get() = itemView.findViewById<TextView>(R.id.userEmailText)
    private val userImageView get() = itemView.findViewById<ImageView>(R.id.userImageListView)

    fun bind(user: User, userClickListener: (User) -> Unit) {
        userFullNameText.setText(user.name)
        userEmailText.setText(user.email)
        userImageView.load(user.picture.medium)

        itemView.setOnClickListener {
            userClickListener(user)
        }
    }
}