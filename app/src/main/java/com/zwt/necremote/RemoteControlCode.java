package com.zwt.necremote;

public class RemoteControlCode {
    // 1,0xe41bdf20,KEY_BACK
    //     b14e
    // 1110 0100 0001 1011
    // 0111 0010 1000 1101
    public static final int[] pattern_exit = {
            // 开头两个数字表示引导码
            9000,4500,
            // 下面两行表示用户码
            560,560, 560,560, 560,560, 560,560,     560,560, 560,1680, 560,560, 560,560,
            560,1680, 560,1680, 560,1680, 560,1680,     560,1680, 560,560, 560,1680, 560,1680,
            // 下面一行表示数据码
            560,1680, 560,1680, 560,560, 560,1680,    560,1680, 560,560, 560,560, 560,560,
            // 下面一行表示数据反码
            560,560, 560,560, 560,1680, 560,560,    560,560, 560,1680, 560,1680, 560,1680,
            // 末尾两个数字表示结束码
            560,20000 };
    //1,0xea15df20,KEY_EPG
    //1110 1010 0001 0101
    //0111 0101 1000 1010
    public static final int[] pattern_epg = {
            // 开头两个数字表示引导码
            9000,4500,
            // 下面两行表示用户码
            560,560, 560,560, 560,560, 560,560,     560,560, 560,1680, 560,560, 560,560,
            560,1680, 560,1680, 560,1680, 560,1680,     560,1680, 560,560, 560,1680, 560,1680,
            // 下面一行表示数据码
            560,1680, 560,560, 560,1680, 560,560,    560,1680, 560,560, 560,560, 560,560,
            // 下面一行表示数据反码
            560,560, 560,1680, 560,560, 560,1680,    560,560, 560,1680, 560,1680, 560,1680,
            // 末尾两个数字表示结束码
            560,20000 };
    //1,0xe817df20,KEY_UP
    //1110 1000 0001 0111
    //0111 0001 1000 1110
    public static final int[] pattern_up = {
            // 开头两个数字表示引导码
            9000,4500,
            // 下面两行表示用户码
            560,560, 560,560, 560,560, 560,560,     560,560, 560,1680, 560,560, 560,560,
            560,1680, 560,1680, 560,1680, 560,1680,     560,1680, 560,560, 560,1680, 560,1680,
            // 下面一行表示数据码
            560,1680, 560,1680, 560,1680, 560,560,   560,1680, 560,560, 560,560, 560,560,
            // 下面一行表示数据反码
            560,560, 560,560, 560,560, 560,1680,    560,560, 560,1680, 560,1680, 560,1680,
            // 末尾两个数字表示结束码
            560,20000 };
    //1,0xf20ddf20,KEY_DOWN
    //1111 0010 0000 1101
    //1111 0100 0000 1011
    public static final int[] pattern_down = {
            // 开头两个数字表示引导码
            9000,4500,
            // 下面两行表示用户码
            560,560, 560,560, 560,560, 560,560,     560,560, 560,1680, 560,560, 560,560,
            560,1680, 560,1680, 560,1680, 560,1680,     560,1680, 560,560, 560,1680, 560,1680,
            // 下面一行表示数据码
            560,1680, 560,560, 560,1680, 560,1680,    560,560, 560,560, 560,560, 560,560,
            // 下面一行表示数据反码
            560,560, 560,1680, 560,560, 560,560,    560,1680, 560,1680, 560,1680, 560,1680,
            // 末尾两个数字表示结束码
            560,20000 };
    //1,0xf30cdf20,KEY_LEFT
    // 1111 0011 0000 1100
    // 1111 1100 0000 0011
    public static final int[] pattern_left = {
            // 开头两个数字表示引导码
            9000,4500,
            // 下面两行表示用户码
            560,560, 560,560, 560,560, 560,560,     560,560, 560,1680, 560,560, 560,560,
            560,1680, 560,1680, 560,1680, 560,1680,     560,1680, 560,560, 560,1680, 560,1680,
            // 下面一行表示数据码
            560,560, 560,560, 560,1680, 560,1680,   560,560, 560,560, 560,560, 560,560,
            // 下面一行表示数据反码
            560,1680, 560,1680, 560,560, 560,560,    560,1680, 560,1680, 560,1680, 560,1680,
            // 末尾两个数字表示结束码
            560,20000 };
    //1,0xfa05df20,KEY_RIGHT
    //1111 1010 0000 0101
    //1111 0101 0000 1010
    public static final int[] pattern_right = {
            // 开头两个数字表示引导码
            9000,4500,
            // 下面两行表示用户码
            560,560, 560,560, 560,560, 560,560,     560,560, 560,1680, 560,560, 560,560,
            560,1680, 560,1680, 560,1680, 560,1680,     560,1680, 560,560, 560,1680, 560,1680,
            // 下面一行表示数据码
            560,1680, 560,560, 560,1680, 560,560,   560,560, 560,560, 560,560, 560,560,
            // 下面一行表示数据反码
            560,560, 560,1680, 560,560, 560,1680,   560,1680, 560,1680, 560,1680, 560,1680,
            // 末尾两个数字表示结束码
            560,20000 };
    //1,0xfd02df20,KEY_ENTER
    //1111 1101 0000 0010
    //1111 1011 0000 0100
    public static final int[] pattern_center = {
            // 开头两个数字表示引导码
            9000,4500,
            // 下面两行表示用户码
            560,560, 560,560, 560,560, 560,560,     560,560, 560,1680, 560,560, 560,560,
            560,1680, 560,1680, 560,1680, 560,1680,     560,1680, 560,560, 560,1680, 560,1680,
            // 下面一行表示数据码
            560,560, 560,1680, 560,560, 560,560,    560,560, 560,560, 560,560, 560,560,
            // 下面一行表示数据反码
            560,1680, 560,560, 560,1680, 560,1680,    560,1680, 560,1680, 560,1680, 560,1680,
            // 末尾两个数字表示结束码
            560,20000 };
    //1,0xb14edf20,KEY_MENU
    //1011 0001 0100 1110
    //1101 1000 0010 0111
    public static final int[] pattern_menu = {
            // 开头两个数字表示引导码
            9000,4500,
            // 下面两行表示用户码
            560,560, 560,560, 560,560, 560,560,     560,560, 560,1680, 560,560, 560,560,
            560,1680, 560,1680, 560,1680, 560,1680,     560,1680, 560,560, 560,1680, 560,1680,
            // 下面一行表示数据码
            560,560, 560,1680, 560,1680, 560,1680,    560,560, 560,560, 560,1680, 560,560,
            // 下面一行表示数据反码
            560,1680, 560,560, 560,560, 560,560,    560,1680, 560,1680, 560,560, 560,1680,
            // 末尾两个数字表示结束码
            560,20000 };
    //1,0xfe01df20,KEY_INPUT
    //1111 1110 0000 0001
    //1111 0111 0000 1000
    public static final int[] pattern_source = {
            // 开头两个数字表示引导码
            9000,4500,
            // 下面两行表示用户码
            560,560, 560,560, 560,560, 560,560,     560,560, 560,1680, 560,560, 560,560,
            560,1680, 560,1680, 560,1680, 560,1680,     560,1680, 560,560, 560,1680, 560,1680,
            // 下面一行表示数据码
            560,1680, 560,560, 560,560, 560,560,    560,560, 560,560, 560,560, 560,560,
            // 下面一行表示数据反码
            560,560, 560,1680, 560,1680, 560,1680,     560,1680, 560,1680, 560,1680, 560,1680,
            // 末尾两个数字表示结束码
            560,20000 };
    public static final int[] pattern_power = {
            9000, 4500, 560, 560, 560, 560, 560, 560, 560, 560, 560, 560, 560, 1680, 560, 560, 560, 560, 560, 1680, 560, 1680, 560, 1680, 560, 1680, 560, 1680, 560, 560, 560, 1680, 560, 1680, 560, 1680, 560, 1680, 560, 560, 560, 1680, 560, 560, 560, 560, 560, 560, 560, 560, 560, 560, 560, 560, 560, 1680, 560, 560, 560, 1680, 560, 1680, 560, 1680, 560, 1680, 560, 20000
    };
    public static final int[] pattern_home = {
            9000, 4500, 560, 560, 560, 560, 560, 560, 560, 560, 560, 560, 560, 1680, 560, 560, 560, 560, 560, 1680, 560, 1680, 560, 1680, 560, 1680, 560, 1680, 560, 560, 560, 1680, 560, 1680, 560, 560, 560, 1680, 560, 1680, 560, 560, 560, 560, 560, 560, 560, 560, 560, 560, 560, 1680, 560, 560, 560, 560, 560, 1680, 560, 1680, 560, 1680, 560, 1680, 560, 1680, 560, 20000
    };
    public static final int[] pattern_CH_up = {
            9000, 4500, 560, 560, 560, 560, 560, 560, 560, 560, 560, 560, 560, 1680, 560, 560, 560, 560, 560, 1680, 560, 1680, 560, 1680, 560, 1680, 560, 1680, 560, 560, 560, 1680, 560, 1680, 560, 1680, 560, 560, 560, 1680, 560, 560, 560, 1680, 560, 560, 560, 1680, 560, 560, 560, 560, 560, 1680, 560, 560, 560, 1680, 560, 560, 560, 1680, 560, 560, 560, 1680, 560, 20000
    };
    public static final int[] pattern_CH_dowm = {
            9000, 4500, 560, 560, 560, 560, 560, 560, 560, 560, 560, 560, 560, 1680, 560, 560, 560, 560, 560, 1680, 560, 1680, 560, 1680, 560, 1680, 560, 1680, 560, 560, 560, 1680, 560, 1680, 560, 560, 560, 1680, 560, 560, 560, 1680, 560, 1680, 560, 560, 560, 1680, 560, 560, 560, 1680, 560, 560, 560, 1680, 560, 560, 560, 560, 560, 1680, 560, 560, 560, 1680, 560, 20000
    };
    public static final int[] pattern_VOL_up = {
            9000, 4500, 560, 560, 560, 560, 560, 560, 560, 560, 560, 560, 560, 1680, 560, 560, 560, 560, 560, 1680, 560, 1680, 560, 1680, 560, 1680, 560, 1680, 560, 560, 560, 1680, 560, 1680, 560, 560, 560, 1680, 560, 560, 560, 1680, 560, 560, 560, 560, 560, 560, 560, 560, 560, 1680, 560, 560, 560, 1680, 560, 560, 560, 1680, 560, 1680, 560, 1680, 560, 1680, 560, 20000
    };
    public static final int[] pattern_VOL_dowm = {
            9000, 4500, 560, 560, 560, 560, 560, 560, 560, 560, 560, 560, 560, 1680, 560, 560, 560, 560, 560, 1680, 560, 1680, 560, 1680, 560, 1680, 560, 1680, 560, 560, 560, 1680, 560, 1680, 560, 560, 560, 560, 560, 560, 560, 560, 560, 560, 560, 560, 560, 1680, 560, 560, 560, 1680, 560, 1680, 560, 1680, 560, 1680, 560, 1680, 560, 1680, 560, 560, 560, 1680, 560, 20000
    };
    public static final int[] pattern_chrom = {
            9000, 4500, 560, 560, 560, 560, 560, 560, 560, 560, 560, 560, 560, 1680, 560, 560, 560, 560, 560, 1680, 560, 1680, 560, 1680, 560, 1680, 560, 1680, 560, 560, 560, 1680, 560, 1680, 560, 560, 560, 560, 560, 560, 560, 1680, 560, 1680, 560, 560, 560, 1680, 560, 560, 560, 1680, 560, 1680, 560, 1680, 560, 560, 560, 560, 560, 1680, 560, 560, 560, 1680, 560, 20000
    };
}
