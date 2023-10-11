package com.example.b11;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;
import android.os.Process;

public class DemoService extends Service {
    private Looper mServiceLooper;
    private ServiceHandler mServiceHandler;

    // Lớp ServiceHandler là một lớp con của Handler và được sử dụng để xử lý các thông điệp từ luồng
    // Xử lý công việc của dịch vụ trong handleMessage()
    private final class ServiceHandler extends Handler {
        public ServiceHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            // Trong phần này, bạn thường sẽ thực hiện một số công việc, như tải tệp tin từ internet hoặc xử lý dữ liệu.
            // Ở đây, ta chỉ đơn giản là gọi Thread.currentThread().interrupt() để khôi phục trạng thái interrupted.
            // Interrupted status cho biết rằng luồng đã bị interrupt (ngắt), và ta khôi phục nó trở lại.
            Thread.currentThread().interrupt();

            // Dùng stopSelf để dừng dịch vụ với startId cụ thể.
            // Điều này đảm bảo rằng dịch vụ chỉ dừng khi đã hoàn thành xử lý công việc được gửi tới.
            stopSelf(msg.arg1);
        }
    }

    @Override
    public void onCreate() {
        // Bắt đầu một luồng mới để chạy dịch vụ.
        // Lưu ý rằng chúng ta tạo một luồng riêng biệt vì dịch vụ thường chạy trong luồng chính của quy trình,
        // và chúng ta không muốn chặn (block) luồng chính này.
        // Chúng ta cũng đặt độ ưu tiên của luồng thành BACKGROUND để công việc tốn CPU không làm ảnh hưởng đến giao diện người dùng.
        HandlerThread thread = new HandlerThread("ServiceStartArguments", Process.THREAD_PRIORITY_BACKGROUND);
        thread.start();

        // Lấy Looper của HandlerThread và sử dụng nó cho Handler của chúng ta.
        mServiceLooper = thread.getLooper();
        mServiceHandler = new ServiceHandler(mServiceLooper);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "service starting", Toast.LENGTH_SHORT).show();
        // For each start request, send a message to start a job and deliver the
        // start ID so we know which request we're stopping when we finish the job
        Message msg = mServiceHandler.obtainMessage();
        msg.arg1 = startId;
        mServiceHandler.sendMessage(msg);
        // If we get killed, after returning from here, restart
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;// We don't provide binding, so return null
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "service done", Toast.LENGTH_SHORT).show();
    }
}
