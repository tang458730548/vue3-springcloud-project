<template>
  <TheLogin v-if="!user.loginName"></TheLogin>
  <div v-if="user.loginName" style="width: 100%; height: 100%">
    <a-layout style="width: 100%; height: 100%">
      <TheHeader></TheHeader>
      <a-layout-content class="home">
        <a-layout style="width: 100%; height:100%; background: #fff">
          <a-layout-sider
              width="200" style="overflow: auto;background-color: #eee;border: 1px solid #ddd">
            <div class="content-header"><p style="font-size: 16px; text-align: center">功能菜单</p></div>
            <a-menu
                style="background-color: #eee;"
                v-model:openKeys="openKeys"
                v-model:selectedKeys="selectKeys"
                mode="inline"
                @click="click"
            >
              <a-sub-menu v-for="item in moduleList" :key="item.id" style="border-bottom: 1px solid #fff">
                <template v-slot:title>
                  <span><laptop-outlined/>&nbsp;&nbsp;{{ item.moduleName }}</span>
                </template>
                <a-menu-item v-for="child in item.children" :key="child.id">
                  <span>{{ child.moduleName }}</span>
                </a-menu-item>
              </a-sub-menu>
            </a-menu>
          </a-layout-sider>
          <a-layout-content :style="{minHeight: '400px', height: '100%'}">
            <div class="content-header"></div>
            <div class="content-router">
              <router-view/>
            </div>
          </a-layout-content>
        </a-layout>
      </a-layout-content>
      <TheFooter></TheFooter>
    </a-layout>
  </div>
</template>

<script lang="ts">
import {computed, defineComponent, onMounted, ref} from 'vue';
import TheLogin from "@/components/the-login.vue";
import TheHeader from "@/components/the-header.vue";
import TheFooter from "@/components/the-footer.vue";
import Home from '@/views/Home.vue';
import store from "@/store";
import axios from "axios";
import {Tool} from "@/util/tool";
import {message} from "ant-design-vue";
import router from "@/router";

export default defineComponent({
  name: 'app',
  components: {
    TheLogin,
    TheHeader,
    TheFooter,
    Home
  },
  setup() {

    const user = computed(() => store.state.user);
    let modules: any;
    const moduleList = ref();
    moduleList.value = []

    let openKeys = ref();
    const selectKeys = ref();
    selectKeys.value = [101]

    const handleQueryModuleList = () => {
      axios.post('admin/module/list').then(
          response => {
            const result = response.data
            if (result.code == 200) {
              modules = result.data
              moduleList.value = Tool.array2Tree(result.data, 0);
              console.log(moduleList.value)
              if (result.data) {
                const openKeyArr: any[] = [];
                openKeyArr.push(result.data[0].id)
                openKeys.value = openKeyArr
              }
            } else {
              message.error(result.message);
            }
          }
      )
    }

    const click = (value: any) => {
      console.log(value)
      const key = value.key

      //获取url
      const module = modules.find((item: any) => {
        return item.id == key
      })
      console.log(module)
      router.push(module.moduleUrl)
    }

    onMounted(() => {
      handleQueryModuleList()
    })

    return {
      openKeys,
      selectKeys,
      moduleList,
      click,
      user,
      modules
    };
  }
});
</script>

<style>

.home {
  height: calc(100% - 40px - 40px);
  width: 100%;
}

#components-layout-demo-top-side .logo {
  float: left;
  width: 120px;
  height: 31px;
  margin: 16px 24px 16px 0;
  background: rgba(255, 255, 255, 0.3);
}

.ant-row-rtl #components-layout-demo-top-side .logo {
  float: right;
  margin: 16px 0 16px 24px;
}

/*.site-layout-background {*/
/*  !*background: #fff;*!*/
/*}*/
.content-header {
  width: 100%;
  height: 48px;
  line-height: 48px;
  background: #eee;
  border: 1px solid #ddd;
}

.content-router {
  height: calc(100% - 48px - 40px);
  overflow: auto;
}
</style>
