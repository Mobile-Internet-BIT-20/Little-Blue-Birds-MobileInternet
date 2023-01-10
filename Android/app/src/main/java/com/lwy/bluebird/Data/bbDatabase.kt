package com.lwy.bluebird.Data

import android.content.Context

class bbDatabase {
    companion object {
        fun getDataList(context: Context): List<bbData>{
            val dataList = mutableListOf<bbData>()
            for (i in 1..6){
                var imgID = context.resources.getIdentifier(
                    "pic$i",
                    "drawable", context.packageName
                )
                dataList.add(bbData("图片$i",imgID))
            }
            return dataList
        }
    }
}
