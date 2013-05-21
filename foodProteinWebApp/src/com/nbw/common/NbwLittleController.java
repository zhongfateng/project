package com.nbw.common;

import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 * 只是直接集成spring mvc的基类 MultiActionController
 * 用于特殊的情况，没有domain对象的情况，比如说 文件上传
 */
public class NbwLittleController extends MultiActionController {

}
