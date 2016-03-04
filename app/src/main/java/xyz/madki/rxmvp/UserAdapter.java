package xyz.madki.rxmvp;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;

/**
 * Created by madki on 29/02/16.
 */
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.Holder> {
  private MainPresenter presenter;
  private List<GitHubUser> users;

  private Observable<Integer> closeClickStream;

  @Inject public UserAdapter(MainPresenter presenter) {
    this.presenter = presenter;
  }

  @Override public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = View.inflate(parent.getContext(), R.layout.list_item_user, parent);
    return new Holder(view);
  }

  @Override public void onBindViewHolder(Holder holder, int position) {
    if(users != null && position >= 0 && position < getItemCount()) {
      GitHubUser user = users.get(position);
      Picasso.with(holder.avatar.getContext()).load(user.image()).into(holder.avatar);
      holder.name.setText(user.name());
    }
  }

  @Override public int getItemCount() {
    if(users != null) return users.size();
    return 0;
  }

  public Observable<Integer> getCloseClickStream() {
    return closeClickStream;
  }

  static class Holder extends RecyclerView.ViewHolder {
    @Bind(R.id.iv_avatar) ImageView avatar;
    @Bind(R.id.tv_name) TextView name;
    @Bind(R.id.iv_close) View close;

    private Observable<Integer> closeClickStream;

    public Holder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);

      RxView.clicks(close)
              .map(aVoid -> getAdapterPosition())
    }

    public Observable<Integer> getCloseClickStream() {
      return closeClickStream;
    }
  }

}
