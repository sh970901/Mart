package com.hun.market.base.initData;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractInitData {

    public void before(){
      log.info("Init Data process");
    }
}
