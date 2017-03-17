package com.fable.dsp.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 执行命令行.
 * 
 * @author  Yi Hong Wei
 */
public class SysAuthExecCmd {

    /**
     * 执行前执行.
     */
    public void beforeExec() {}

    /**
     * 执行后执行.
     */
    public void afterExec() {}

    /**
     * 新开线程执行命令.
     * 
     * @param cmd
     *        cmd
     */
    public void execInNewThread(final String cmd) {
        this.execInNewThread(cmd, null, new File("."), null);
    }

    /**
     * 新开线程执行命令.
     * 
     * @param cmd
     *        cmd
     * @param envp
     *        环境变量
     * @param dir
     *        开始路径
     */
    public void execInNewThread(final String cmd, final String[] envp,
        final File dir) {
        this.execInNewThread(cmd, envp, dir, null);
    }

    /**
     * 新开线程执行命令.
     * 
     * @param cmd
     *        cmd
     * @param envp
     *        环境变量
     * @param dir
     *        开始路径
     * @param logFile
     *        日志文件
     */
    public void execInNewThread(final String cmd, final String[] envp,
        final File dir, final String logFile) {
        new Thread(new Runnable() {

            public void run() {
                SysAuthExecCmd.this.exec(cmd, envp, dir, logFile);
            }
        }).start();
    }

    /**
     * 执行命令.
     * 
     * @param cmd
     *        cmd
     */
    public void exec(final String cmd) {
        this.exec(cmd, null, new File("."), (String)null);
    }

    /**
     * 执行命令.
     * 
     * @param cmd
     *        cmd
     * @param envp
     *        环境变量
     * @param dir
     *        开始路径
     */
    public void exec(final String cmd, final String[] envp, final File dir) {
        this.exec(cmd, envp, dir, (String)null);
    }

    /**
     * 执行命令.
     * 
     * @param cmd
     *        cmd
     * @param envp
     *        环境变量
     * @param dir
     *        开始路径
     * @param out
     *        日志输出流
     */
    public void exec(final String[] cmd, final String[] envp,
        final File dir, OutputStream out) {
        this.beforeExec();
        try {
            final Process process =
                Runtime.getRuntime().exec(cmd, envp, dir);
            if (out == null)
                out = System.out;
            this.createConsoleThread(process, out);
            process.waitFor();
        }
        catch (final Exception e) {
            e.printStackTrace();
        }
        this.afterExec();
    }

    /**
     * 执行命令.
     * 
     * @param cmd
     *        cmd
     * @param envp
     *        环境变量
     * @param dir
     *        开始路径
     * @param out
     *        日志输出流
     */
    public void exec(final String cmd, final String[] envp, final File dir,
        OutputStream out) {
        this.beforeExec();
        try {
            final Process process =
                Runtime.getRuntime().exec(cmd, envp, dir);
            if (out == null)
                out = System.out;
            this.createConsoleThread(process, out);
            process.waitFor();
        }
        catch (final Exception e) {
            e.printStackTrace();
        }
        this.afterExec();
    }

    /**
     * 执行命令.
     * 
     * @param cmd
     *        cmd
     * @param envp
     *        环境变量
     * @param dir
     *        开始路径
     * @param logFile
     *        日志文件
     */
    public void exec(final String cmd, final String[] envp, final File dir,
        final String logFile) {
        OutputStream out = null;
        if (logFile != null)
            try {
                out = new FileOutputStream(new File(logFile), true);
                this.exec(cmd, envp, dir, out);
            }
            catch (final Exception e) {
                e.printStackTrace();
            }
            finally {
                if (out != null && out != System.out)
                    try {
                        out.close();
                    }
                    catch (final Exception e) {
                        e.printStackTrace();
                    }
            }
    }

    /**
     * 起新线程，记录process控制台输出到out中.
     * 
     * @param process
     *        process
     * @param out
     *        out
     */
    public void createConsoleThread(final Process process,
        final OutputStream out) {
        final Thread outThread = new Thread(new Runnable() {

            public void run() {
                InputStream in = null;
                try {
                    in = process.getInputStream();
                    final byte[] buf = new byte[100];
                    for (int i = in.read(buf); i >= 0; i = in.read(buf))
                        out.write(buf, 0, i);
                }
                catch (final Exception e) {
                    e.printStackTrace();
                }
                finally {
                    if (in != null)
                        try {
                            in.close();
                        }
                        catch (final IOException e) {
                            e.printStackTrace();
                        }
                }
            }
        });
        outThread.start();
        final Thread errThread = new Thread(new Runnable() {

            public void run() {
                InputStream in = null;
                try {
                    in = process.getErrorStream();
                    final byte[] buf = new byte[100];
                    for (int i = in.read(buf); i >= 0; i = in.read(buf))
                        out.write(buf, 0, i);
                }
                catch (final Exception e) {
                    e.printStackTrace();
                }
                finally {
                    if (in != null)
                        try {
                            in.close();
                        }
                        catch (final IOException e) {
                            e.printStackTrace();
                        }
                }
            }
        });
        errThread.start();
        try {
            outThread.join();
        }
        catch (final InterruptedException e) {
            e.printStackTrace();
        }
        try {
            errThread.join();
        }
        catch (final InterruptedException e) {
            e.printStackTrace();
        }
    }
}
