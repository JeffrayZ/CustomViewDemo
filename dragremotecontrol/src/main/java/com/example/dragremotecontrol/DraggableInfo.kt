package com.example.dragremotecontrol

import java.io.Serializable

/**
 * @ProjectName: CustomViewDemo
 * @Package: com.example.dragremotecontrol
 * @ClassName: DraggableInfo
 * @Description: java类作用描述
 * @Author: Jeffray
 * @CreateDate: 2020/6/1 14:26
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/6/1 14:26
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
data class DraggableInfo(var pic: Int, var posX: Float, var posY: Float) : Serializable