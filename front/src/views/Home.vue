<template>
  <div style="padding : 15px; text-align: center; margin: 0 auto">
    <a-row :span="24" :gutter="16">
      <a-col :span="6">
        <div style="margin: 0 auto">
          <a-card size="small" title="">
            <a-statistic title="在线人数" :value="1128" style="margin-right: 50px">
              <template #suffix>
                <like-outlined/>
              </template>
            </a-statistic>
          </a-card>
        </div>
      </a-col>
      <a-col :span="6">
        <div style="margin: 0 auto">
          <a-card size="small" title="">
            <a-statistic title="总浏览量" :value="22222" style="margin-right: 50px">
              <template #suffix>
                <like-outlined/>
              </template>
            </a-statistic>
          </a-card>
        </div>
      </a-col>
      <a-col :span="6">
        <div style="margin: 0 auto">
          <a-card size="small" title="">
            <a-statistic
                title="访问增长率"
                :value="1.28"
                :precision="2"
                suffix="%"
                :value-style="{ color: '#3f8600' }"
                style="margin-right: 50px"
            >
              <template #prefix>
                <arrow-up-outlined/>
              </template>
            </a-statistic>
          </a-card>
        </div>
      </a-col>
      <a-col :span="6">
        <div style="margin: 0 auto;">
          <a-card size="small" title="">
            <a-statistic
                title="当前时间"
                :value="nowTime"
                style="margin-right: 50px"
            />
          </a-card>
        </div>
      </a-col>
    </a-row>

    <a-row :span="24" :gutter="16">
      <a-col :span="24">
        <div id="main" style="width: 100%; height: 300px; margin-top:15px;background-color: #fff; padding: 15px ">

        </div>
      </a-col>
    </a-row>

    <a-row :span="24" :gutter="16">
      <a-col :span="18">
        <div style="width: 100%; height: 500px; margin-top:15px;background-color: #fff; padding: 20px">
          <a-row :span="24" :gutter="16">
            <a-col :span="12">
              <div id="pie1" style="height: 240px;">
              </div>
            </a-col>
            <a-col :span="12">
              <div id="pie2" style="height: 240px;">
              </div>
            </a-col>
          </a-row>
          <hr/>
          <a-row :span="24" :gutter="16">
            <a-col :span="24">
              <div style="height: 240px; margin-top: 20px">
                <div id="bar" style="height: 240px;">
                </div>
              </div>
            </a-col>
          </a-row>
        </div>
      </a-col>
      <a-col :span="6">
        <div
            style="width: 100%; height: 500px; margin-top:15px;background-color: #fff; text-align: left ; padding: 20px">
          <h3 style="font-weight: bolder; margin-bottom: 20px">排名情况概述</h3>
          <p>
            <a-tag color="#f50">#1</a-tag>
            第一名
          </p>
          <p>
            <a-tag color="#2db7f5">#2</a-tag>
            第二名
          </p>
          <p>
            <a-tag color="#87d068">#3</a-tag>
            第三名
          </p>
          <p>
            <a-tag color="">#4</a-tag>
            第四名
          </p>
          <p>
            <a-tag color="">#5</a-tag>
            第五名
          </p>
        </div>
      </a-col>
    </a-row>

  </div>
</template>

<script lang="ts">
import {defineComponent, onMounted, ref} from "vue";
import {LikeOutlined, ArrowUpOutlined, TwitterOutlined} from '@ant-design/icons-vue';

declare let echarts: any;
export default defineComponent({
  name: "home",
  components: {
    LikeOutlined,
    ArrowUpOutlined,
    TwitterOutlined
  },

  setup() {
    const onFinish = () => {
      console.log('finished!');
    };

    const loadLineEchart = () => {
      // 基于准备好的dom，初始化echarts实例
      const myChart = echarts.init(document.getElementById('main'));

      const xAxis = ['一月', '二月', '三月', '四月'];
      const seriesView = [20, 20, 3, 40];
      const seriesVote = [10, 200, 30, 50];

      // 指定图表的配置项和数据
      const option = {
        title: {
          text: '30天趋势图'
        },
        tooltip: {
          trigger: 'axis'
        },
        legend: {
          data: ['总阅读量', '总点赞量']
        },
        grid: {
          left: '1%',
          right: '3%',
          bottom: '3%',
          containLabel: true
        },
        toolbox: {
          feature: {
            saveAsImage: {}
          }
        },
        xAxis: {
          type: 'category',
          boundaryGap: false,
          data: xAxis
        },
        yAxis: {
          type: 'value'
        },
        series: [
          {
            name: '总阅读量',
            type: 'line',
            // stack: '总量', 不堆叠
            data: seriesView,
            smooth: true
          },
          {
            name: '总点赞量',
            type: 'line',
            // stack: '总量', 不堆叠
            data: seriesVote,
            smooth: true
          }
        ]
      };

      // 使用刚指定的配置项和数据显示图表。
      myChart.setOption(option);
    }

    const loadPieEcharts = () => {
      const myChart = echarts.init(document.getElementById('pie1'));
      const myChart2 = echarts.init(document.getElementById('pie2'));
      const option = {
        title: {
          text: 'Referer of a Website',
          subtext: 'Fake Data',
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
            name: 'Access From',
            type: 'pie',
            radius: '50%',
            data: [
              {value: 1048, name: 'Search Engine'},
              {value: 735, name: 'Direct'},
              {value: 580, name: 'Email'},
              {value: 484, name: 'Union Ads'},
              {value: 300, name: 'Video Ads'}
            ],
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
      // 使用刚指定的配置项和数据显示图表。
      myChart.setOption(option);
      myChart2.setOption(option)
    }

    const loadBarEcharts = () => {
      const myChart = echarts.init(document.getElementById('bar'));
      const option = {
        legend: {},
        tooltip: {},
        dataset: {
          source: [
            ['product', '2015', '2016', '2017'],
            ['Matcha Latte', 43.3, 85.8, 93.7],
            ['Milk Tea', 83.1, 73.4, 55.1],
            ['Cheese Cocoa', 86.4, 65.2, 82.5],
            ['Walnut Brownie', 72.4, 53.9, 39.1]
          ]
        },
        xAxis: {type: 'category'},
        yAxis: {},
        // Declare several bar series, each will be mapped
        // to a column of dataset.source by default.
        series: [{type: 'bar'}, {type: 'bar'}, {type: 'bar'}]
      };
      // 使用刚指定的配置项和数据显示图表。
      myChart.setOption(option);
    }

    const nowTime = ref()

    const formatTime = (data: Date | string, flag: boolean) => {
      const date = new Date(data);
      const y = date.getFullYear();
      let m: number | string = date.getMonth() + 1;
      m = m < 10 ? '0' + m : m;
      let d: number | string = date.getDate();
      d = d < 10 ? '0' + d : d;
      let h: number | string = date.getHours();
      h = h < 10 ? '0' + h : h;
      let minute: number | string = date.getMinutes();
      minute = minute < 10 ? '0' + minute : minute;
      let second: number | string = date.getSeconds();
      second = second < 10 ? '0' + second : second;
      if (flag) {
        return y + '-' + m + '-' + d;
      }
      return y + '-' + m + '-' + d + ' ' + h + ':' + minute + ':' + second;
    };

    onMounted(() => {
      setInterval(() => {
        nowTime.value = formatTime(new Date(), false)
      })
      loadLineEchart()
      loadPieEcharts()
      loadBarEcharts()
    })

    return {
      nowTime,
      onFinish,
    };
  }
})
;
</script>
