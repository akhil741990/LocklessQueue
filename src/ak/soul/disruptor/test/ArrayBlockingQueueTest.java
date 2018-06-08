package ak.soul.disruptor.test;

import java.util.concurrent.ArrayBlockingQueue;

import ak.soul.disruptor.TaskPoolQueueEntry;

public class ArrayBlockingQueueTest {

	public static void main(String args[]){
		ArrayBlockingQueue<String> q = new ArrayBlockingQueue<>(10);
		
		
		Thread producerThread =  new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("Producer Thread  started");
				int i = 0;
				while(true){

					
					try {
						String s = new String(""+i);
						q.put(s);
						System.out.println("Produced :"+ i);
						i++;
						Thread.sleep(1000*1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
					
			}
		});
		
		producerThread.setName("ProducerThread");
		
		Thread consumerThread1 =  new Thread(new Runnable() {
			
			@Override
			public void run() {
			
				System.out.println("Consumer Thread 1 started");
				while(true){
					
					try {
						String t = q.take();
						System.out.println("Consumer Thread 1 Consumed :"+t);
						Thread.sleep(1000*10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		
		consumerThread1.setName("ConsumerThread1");
		Thread consumerThread2 =  new Thread(new Runnable() {
			
			@Override
			public void run() {
			
				System.out.println("Consumer Thread 2 started");
				while(true){
					
					try {
						String t = q.take();
						System.out.println("Consumer Thread 2 Consumed :"+t);
						Thread.sleep(1000*20);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
		});
		
	
		consumerThread2.setName("ConsumerThread2");
		Thread consumerThread3 =  new Thread(new Runnable() {
			
			@Override
			public void run() {
			
				System.out.println("Consumer Thread 3 started");
				while(true){
					
					try {
						String t = q.take();
						System.out.println("Consumer Thread 3 Consumed :"+t);
						Thread.sleep(1000*30);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
		});
	
		consumerThread3.setName("ConsumerThread3");
		//Starting Consumers
		
		consumerThread1.start();
		consumerThread2.start();
		consumerThread3.start();
		
		
		// Strating Producer Thread
		
		producerThread.start();
		
		
		try {
			producerThread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
