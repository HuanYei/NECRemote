package com.zwt.necremote;

import android.content.Context;
import android.hardware.ConsumerIrManager;
import android.os.Build;

public class ConsumerIrManagerApi {

    private static volatile ConsumerIrManagerApi consumerIrManagerApi;
    private static ConsumerIrManager service;

    private ConsumerIrManagerApi(){}

    // 单例模式
    public static ConsumerIrManagerApi getInstance() {
        if(consumerIrManagerApi == null) {
            synchronized(ConsumerIrManagerApi.class) {
                if(consumerIrManagerApi == null) {
                    consumerIrManagerApi = new ConsumerIrManagerApi();
                }
            }
        }
        return consumerIrManagerApi;
    }

    // 初始化
    public void init(Context context){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            service = (ConsumerIrManager) context.getApplicationContext().getSystemService(Context.CONSUMER_IR_SERVICE);
        }
    }
    // 判断手机是否支持红外遥控
    public static boolean hasIrEmitter() {
        //android4.4及以上版本&有红外功能
        if(service!=null){
            return service.hasIrEmitter();
        }
        //android4.4以下及4.4以上没红外功能
        return false;
    }
    // 发射红外信号
    public static boolean transmit(int carrierFrequency, int[] pattern) {
        if(service!=null){
            service.transmit(carrierFrequency, pattern);
            return true;
        }
        return false;
    }
    // 接收红外信号
    public static ConsumerIrManager.CarrierFrequencyRange[] getCarrierFrequencies() {
        if(service!=null){
            return service.getCarrierFrequencies();
        }
        return null;
    }

}
