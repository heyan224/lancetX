//
//                            _ooOoo_  
//                           o8888888o  
//                           88" . "88  
//                           (| -_- |)  
//                            O\ = /O  
//                        ____/`---'\____  
//                      .   ' \\| |// `.  
//                       / \\||| : |||// \  
//                     / _||||| -:- |||||- \  
//                       | | \\\ - /// | |  
//                     | \_| ''\---/'' | |  
//                      \ .-\__ `-` ___/-. /  
//                   ___`. .' /--.--\ `. . __  
//                ."" '< `.___\_<|>_/___.' >'"".  
//               | | : `- \`.;`\ _ /`;.`/ - ` : | |  
//                 \ \ `-. \_ __\ /__ _/ .-` / /  
//         ======`-.____`-.___\_____/___.-`____.-'======  
//                            `=---='  
//  
//         .............................................
package com.zhangyue.lancet.internal.log.Impl;

import org.gradle.api.logging.LogLevel;
import org.gradle.api.logging.Logger;
import org.gradle.api.logging.Logging;

/**
 * Created by Knight-ZXW on 16/7/6.
 */
public class SystemLoggerImpl extends BaseLogger {

    private final Logger logger = Logging.getLogger("KnightBoostWeaver");

    @Override
    protected synchronized void write(LogLevel level, String prefix, String msg, Throwable t) {
        if (t != null) {
            logger.log(level, String.format("[%-10s] %s", prefix, msg), t);
        } else {
            logger.log(level, String.format("[%-10s] %s", prefix, msg));
        }
    }
}
