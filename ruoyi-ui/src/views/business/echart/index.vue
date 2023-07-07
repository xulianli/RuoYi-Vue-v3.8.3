<template>

  <div id="addressContainer" style="height: 600px"></div>


</template>

<script>
import {getCustomerAddress} from '@/api/business/statistics'
import * as echarts from 'echarts';
export default {
  name: "CustomerAddress",
  data(){
    return{
    }
  },
  // vue的生命周期
  created() {
  },
  mounted() {
    // 页面的DOM元素也渲染完成了
    this.getData();
  },
  methods:{
    getData(){
      getCustomerAddress().then(res=>{

        // code msg  data
        console.log(res.data)
        // 先获取页面元素
        var chartDom = document.getElementById('addressContainer');
        // 用图片对其进行初始化
        var myChart = echarts.init(chartDom);
        // 定义数据
        var option;
        option = {
          title: {
            text: '客户地址区域分析',
            left: 'center'
          },
          tooltip: {
            trigger: 'item'
          },
          legend: {
            orient: 'vertical',
            left: 'left'
          },
          series: [
            {
              name: 'address',
              type: 'pie',
              radius: '50%',
              data: res.data,
              emphasis: {
                itemStyle: {
                  shadowBlur: 10,
                  shadowOffsetX: 0,
                  shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
              }
            }
          ]
        };
        // 将数据设置到初始化对象中去即可
        option && myChart.setOption(option);
      })
    }
  }
}
</script>

<style scoped>

</style>
