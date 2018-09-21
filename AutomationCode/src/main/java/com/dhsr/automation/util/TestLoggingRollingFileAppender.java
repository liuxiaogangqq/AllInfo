package com.dhsr.automation.util;

import java.io.File;
import java.io.IOException;
import java.io.InterruptedIOException;

import org.apache.log4j.Layout;
import org.apache.log4j.RollingFileAppender;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.helpers.CountingQuietWriter;

public class TestLoggingRollingFileAppender extends RollingFileAppender {

    private static final String FILE_NAME_POSTFIX = "_0.log";

    public TestLoggingRollingFileAppender() {
        super();
    }

    public TestLoggingRollingFileAppender(Layout layout, String filename, boolean append) throws IOException {
        super(layout, filename, append);
    }

    public TestLoggingRollingFileAppender(Layout layout, String filename) throws IOException {
        super(layout, filename);
    }
	
	//重写log4j RollingFileAppender的滚动记录log方法，log文件写满时，自动写新文件，log4j默认文件名规则为：*.log0, *.log1...
	//将log文件名规则修改为：*_0.log, *_1.log...该方法在每个日志文件写满时由log4j自动调用
    public void rollOver() {
        File target;
        File file;

        if (qw != null) {
            long size = ((CountingQuietWriter) qw).getCount();
            LogLog.debug("rolling over count=" + size);
        }
        LogLog.debug("maxBackupIndex=" + maxBackupIndex);

        boolean renameSucceeded = true;
        //配置的log文件数目大于0
        if (maxBackupIndex > 0) {
            //获取最老的log文件名，序号最大的是最老的log文件
            file = new File(fileName.substring(0, fileName.length() - FILE_NAME_POSTFIX.length()) + '_' + maxBackupIndex + ".log");
            //删除最老的log文件，不一定存在最老文件，只有当log文件个数等于所配置的最大日志文件数时才删除最老日志文件
			if (file.exists()) {
                renameSucceeded = file.delete();
            }
			//将log文件名的序号加1，如*_2.log变为*_3.log，即最新的log会记录在序号最小的log文件中
            for (int i = maxBackupIndex - 1; i >= 1 && renameSucceeded; i--) {
                //获取log文件名
				file = new File(fileName.substring(0, fileName.length() - FILE_NAME_POSTFIX.length()) + '_' + i + ".log");
                if (file.exists()) {
					//将log文件名的序号加1，最小的序号是2
                    target = new File(fileName.substring(0, fileName.length() - FILE_NAME_POSTFIX.length()) + '_' + (i + 1) + ".log");
                    LogLog.debug("Renaming file " + file + " to " + target);
                    renameSucceeded = file.renameTo(target);
                }
            }
			//将最新写满的log文件命名为序号1
            if (renameSucceeded) {
                //命名*_1.log文件
                target = new File(fileName.substring(0, fileName.length() - FILE_NAME_POSTFIX.length()) + '_' + 1 + ".log");

                this.closeFile();
                file = new File(fileName);
                LogLog.debug("Renaming file " + file + " to " + target);
                renameSucceeded = file.renameTo(target);
                //log文件重命名失败，重新打开log文件继续向里面追加log
                if (!renameSucceeded) {
                    try {
                        this.setFile(fileName, true, bufferedIO, bufferSize);
                    } catch (IOException e) {
                        if (e instanceof InterruptedIOException) {
                            Thread.currentThread().interrupt();
                        }
                        LogLog.error("setFile(" + fileName + ", true) call failed.", e);
                    }
                }
            }
        }
        //所有的log文件都改名成功
        if (renameSucceeded) {
            try {
				//关闭所有滚动的log文件
                this.setFile(fileName, false, bufferedIO, bufferSize);
            } catch (IOException e) {
                if (e instanceof InterruptedIOException) {
                    Thread.currentThread().interrupt();
                }
                LogLog.error("setFile(" + fileName + ", false) call failed.", e);
            }
        }
    }
}