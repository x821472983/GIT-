package com.base.pojo;

import java.text.NumberFormat;
import java.util.ArrayList;

/**
 * 
 * <h2>文件下载进度类</h2>
 * 
 * @ClassName: ProgressBarThread
 * @author Enzo
 * @date 2018年2月6日 下午3:50:50
 */
public class ProgressBarThread implements Runnable {

	private ArrayList<Integer> proList = new ArrayList<Integer>();
	private long progress;// 当前进度
	private long totalSize;// 总大小
	private String proportion;// 当前比例
	private boolean run = true;

	public ProgressBarThread(int totalSize) {
		this.totalSize = totalSize;
		// TODO 创建进度条
	}

	/**
	 * @param progress
	 *            进度
	 */
	public void updateProgress(int progress) {
		synchronized (this.proList) {
			if (this.run) {
				this.proList.add(progress);
				this.proList.notify();
			}
		}
	}

	public void finish() {
		this.run = false;
		// 关闭进度条
	}

	@Override
	public void run() {
		synchronized (this.proList) {
			try {
				while (this.run) {
					if (this.proList.size() == 0) {
						this.proList.wait();
					}
					synchronized (proList) {
						this.progress += this.proList.remove(0);
						// TODO 更新进度条
						NumberFormat nf = NumberFormat.getNumberInstance();
						nf.setMaximumFractionDigits(2);
						proportion = nf.format(this.progress * 1.0 / this.totalSize * 100);// 当前进度
						System.err.println("当前进度：" + proportion + "%");
					}
				}
				System.err.println("下载完成");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}