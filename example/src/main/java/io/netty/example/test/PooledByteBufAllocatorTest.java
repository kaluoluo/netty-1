package io.netty.example.test;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;

public class PooledByteBufAllocatorTest {

    public static void main(String[] args) {

        PooledByteBufAllocator allocator = PooledByteBufAllocator.DEFAULT;

        //ByteBuf buf = allocator.buffer(16*1024);
        ByteBuf buf1 = allocator.buffer(1024);
        ByteBuf buf = allocator.buffer(1024);
        buf.writeInt(32);
        buf1.writeInt(16);
        buf.release();
        buf1.release();

        ByteBuf buf2 = allocator.buffer(1024);
        //System.out.println(buf.readInt());
        System.out.println(buf2.readInt());



        buf.release();

//        // 分配Heap内存
//        PooledByteBufAllocator byteBufAllocator = new PooledByteBufAllocator(false);
//        ByteBuf buf2 = byteBufAllocator.buffer(16*1024);

    }
}
