package com.xzq.module_base.utils;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.SparseArrayCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.widget.TextView;


/**
 * 富文本工具
 * Created by xzq on 2018/7/23.
 */

public class RichTextUtils {

    /**
     * 设置富文本
     *
     * @param textView        TextView
     * @param fromIndex       开始索引
     * @param endIndex        结束索引
     * @param textSize        前景文字字体大小（单位sp）
     * @param foregroundColor 前景颜色
     */
    public static void setSpan(TextView textView,
                               int fromIndex,
                               int endIndex,
                               int textSize,
                               @ColorRes int foregroundColor) {
        setSpan(textView, textView.getText().toString(), fromIndex, endIndex, textSize, foregroundColor);
    }

    /**
     * 设置富文本
     *
     * @param textView        TextView
     * @param src             源文本
     * @param fromIndex       开始索引
     * @param endIndex        结束索引
     * @param textSize        前景文字字体大小（单位sp）
     * @param foregroundColor 前景颜色
     */
    public static void setSpan(TextView textView,
                               String src,
                               int fromIndex,
                               int endIndex,
                               int textSize,
                               @ColorRes int foregroundColor) {
        SparseArrayCompat<Integer> indexArray = new SparseArrayCompat<>();
        indexArray.put(fromIndex, endIndex);
        setSpan(textView, indexArray, src, textSize, foregroundColor);
    }

    /**
     * 设置富文本
     *
     * @param textView        TextView
     * @param src             源文本
     * @param target          前景文本
     * @param textSize        前景文字字体大小（单位sp）
     * @param foregroundColor 前景颜色
     */
    public static void setSpan(TextView textView,
                               String src,
                               String target,
                               int textSize,
                               @ColorRes int foregroundColor) {
        if (TextUtils.isEmpty(src) || TextUtils.isEmpty(target)) {
            return;
        }
        int fromIndex = src.indexOf(target);
        int endIndex = fromIndex + target.length();
        SparseArrayCompat<Integer> indexArray = new SparseArrayCompat<>();
        indexArray.put(fromIndex, endIndex);
        setSpan(textView, indexArray, src, textSize, foregroundColor);
    }

    /**
     * 设置富文本
     *
     * @param textView        TextView
     * @param indexArray      起始索引数组
     * @param src             源文本
     * @param textSize        前景文字字体大小（单位sp）
     * @param foregroundColor 前景颜色
     */
    public static void setSpan(TextView textView,
                               SparseArrayCompat<Integer> indexArray,
                               String src,
                               int textSize,
                               @ColorRes int foregroundColor) {
        if (TextUtils.isEmpty(src) || indexArray.size() <= 0) {
            textView.setText(src);
            return;
        }
        try {
            final Context context = textView.getContext();
            float originalTextSize = textView.getTextSize();
            float proportion = context.getResources().getDisplayMetrics().scaledDensity * textSize / originalTextSize;
            proportion = textSize <= 0 ? 1 : proportion;

            SpannableString span = new SpannableString(src);
            for (int i = 0; i < indexArray.size(); i++) {
                int fromIndex = indexArray.keyAt(i);
                int endIndex = indexArray.valueAt(i);
                span.setSpan(new RelativeSizeSpan(proportion), fromIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                span.setSpan(new ForegroundColorSpan(ContextCompat.getColor(textView.getContext(), foregroundColor)),
                        fromIndex, endIndex, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
            }
            textView.setText(span);
        } catch (Exception e) {
            textView.setText(src);
        }
    }

    /**
     * 设置富文本
     *
     * @param textView        TextView
     * @param indexArray      起始索引数组
     * @param src             源文本
     * @param foregroundColor 前景颜色
     */
    public static void setSpan(TextView textView,
                               SparseArrayCompat<Integer> indexArray,
                               String src,
                               @ColorRes int foregroundColor) {
        if (TextUtils.isEmpty(src) || indexArray.size() <= 0) {
            textView.setText(src);
            return;
        }
        try {
            SpannableString span = new SpannableString(src);
            for (int i = 0; i < indexArray.size(); i++) {
                int fromIndex = indexArray.keyAt(i);
                int endIndex = indexArray.valueAt(i);
                span.setSpan(new RelativeSizeSpan(1), fromIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                span.setSpan(new ForegroundColorSpan(ContextCompat.getColor(textView.getContext(), foregroundColor)),
                        fromIndex, endIndex, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
            }
            textView.setText(span);
        } catch (Exception e) {
            textView.setText(src);
        }
    }
}
