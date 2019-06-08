package phone.androidchess84;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import phone.androidchess84.R;
import phone.pieces.*;

import static phone.androidchess84.ChessGame.board;

public class Adapter extends BaseAdapter {
    private Context context;

    public Adapter(Context context){
        this.context = context;
    }

    @Override
    public int getCount() {
        return 64;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ImageView iv;

        if(view == null){
            iv = new ImageView(context);
            iv.setPadding(0,0,0,0);
            //fit image to center
            iv.setScaleType(ImageView.ScaleType.FIT_CENTER);
            iv.setLayoutParams(new GridView.LayoutParams(100, 100));
        }
        else{

            iv = (ImageView) view;
        }

        iv.setImageResource(getImagePiece(position));
        return iv;
    }


    public int getImagePiece(int position) {

        ChessPiece chessPiece = board.board[position / 8][position % 8];

        if (chessPiece == null) {
            return R.drawable.blank;
        }

        //check if piece is black or white
        if (chessPiece.color == false) {
            if (chessPiece instanceof Pawn)
                return R.drawable.bp;
            else if (chessPiece instanceof Rook)
                return R.drawable.br;
            else if (chessPiece instanceof Knight)
                return R.drawable.bn;
            else if (chessPiece instanceof Bishop)
                return R.drawable.bb;
            else if (chessPiece instanceof Queen)
                return R.drawable.bq;
            else
                return R.drawable.bk;
        } else if (chessPiece.color == true) {
            if (chessPiece instanceof Pawn)
                return R.drawable.wp;
            else if (chessPiece instanceof Rook)
                return R.drawable.wr;
            else if (chessPiece instanceof Knight)
                return R.drawable.wn;
            else if (chessPiece instanceof Bishop)
                return R.drawable.wb;
            else if (chessPiece instanceof Queen)
                return R.drawable.wq;
            else
                return R.drawable.wk;
        } else {
            //If it gets here, we messed up
            return R.drawable.blank;
        }
    }

}
