package com.qianxin.call;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFutureCallback;
@Component
public class KafKaCallback implements ListenableFutureCallback<Object> {
    Logger logger = LoggerFactory.getLogger(KafKaCallback.class);


    @Override
    public void onFailure(Throwable ex) {
        logger.error(ex.getMessage());
    }

    @Override
    public void onSuccess(Object result) {
        logger.info("the result:" + result+"has been produced" );
    }
}
