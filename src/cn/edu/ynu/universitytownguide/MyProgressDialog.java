package cn.edu.ynu.universitytownguide;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.view.Gravity;
import android.webkit.WebChromeClient;
import android.widget.ImageView;
import android.widget.TextView;
 
 
/********************************************************************
 * [Summary]
 *       TODO 请在此处简要描述此类所实现的功能。因为这项注释主要是为了在IDE环境中生成tip帮助，务必简明扼要
 * [Remarks]
 *       TODO 请在此处详细描述类的功能、调用方法、注意事项、以及与其它类的关系.
 *******************************************************************/
 
public class MyProgressDialog extends Dialog {
    private Context context = null;
    private static MyProgressDialog customProgressDialog = null;
     
    public MyProgressDialog(Context context){
        super(context);
        this.context = context;
    }
     
    public MyProgressDialog(Context webChromeClient, int theme) {
        super(webChromeClient, theme);
    }
     
    public static MyProgressDialog createDialog(Context webChromeClient){
        customProgressDialog = new MyProgressDialog(webChromeClient,R.style.CustomProgressDialog);
        customProgressDialog.setContentView(R.layout.customprogressdialog);
        customProgressDialog.getWindow().getAttributes().gravity = Gravity.CENTER;
         
        return customProgressDialog;
    }
  
    public void onWindowFocusChanged(boolean hasFocus){
         
        if (customProgressDialog == null){
            return;
        }
         
//        ImageView imageView = (ImageView) customProgressDialog.findViewById(R.id.loadingImageView);
//        AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getBackground();
//        animationDrawable.start();
    }
  
    /**
     *
     * [Summary]
     *       setTitile 标题
     * @param strTitle
     * @return
     *
     */
    public MyProgressDialog setTitile(String strTitle){
        return customProgressDialog;
    }
     
    /**
     *
     * [Summary]
     *       setMessage 提示内容
     * @param strMessage
     * @return
     *
     */
    public MyProgressDialog setMessage(Typeface font){
        TextView tvMsg = (TextView)customProgressDialog.findViewById(R.id.id_tv_loadingmsg);
         
        if (tvMsg != null){
        	tvMsg.setTypeface(font);
            tvMsg.setText("\ue808");
        }
         
        return customProgressDialog;
    }
}
