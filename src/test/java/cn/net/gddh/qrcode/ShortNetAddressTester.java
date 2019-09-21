package cn.net.gddh.qrcode;

import cn.net.gddh.qrcode.utils.ShortNetAddressUtils;

public class ShortNetAddressTester {
    public static void main(String[] args) {
        String url = "https://www.baidu.com/s?wd=%E7%A0%81%E4%BA%91&rsv_spt=1&rsv_iqid=0x964c1c3500030761&issp=1&f=8&rsv_bp=1&rsv_idx=2&ie=utf-8&rqlang=cn&tn=48021271_11_hao_pg&rsv_enter=1&oq=spring%2520data%2520jpa%25E8%2587%25AA%25E5%25AE%259A%25E4%25B9%2589%25E6%2596%25B9%25E6%25B3%2595%25E8%25A7%2584%25E8%258C%2583&rsv_t=d5f86AreI8IAKjbxCKuUMFkUO6EKFeso61joDwkdQQrpLo%2BgDuuyujzyCafz%2B5gC8IMw8TR3iRoe&rsv_pq=f35da7870001b1ba&inputT=7063&rsv_sug3=74&rsv_sug1=50&rsv_sug7=100&bs=spring%20data%20jpa%E8%87%AA%E5%AE%9A%E4%B9%89%E6%96%B9%E6%B3%95%E8%A7%84%E8%8C%83";
        String add = ShortNetAddressUtils.generateShortUrl(url);
        System.out.println(add);
    }
}
