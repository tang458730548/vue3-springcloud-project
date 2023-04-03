<template>
  <a-layout-header class="header">
    <a-row>
      <a-col :span="1">
        <img style="width: 40px;height: 40px;" src="/favicon.ico"/>
      </a-col>
      <a-col :span="5">
        <router-link to="/">
          <span style="color: white; font-size: 18px">xxxxxxx管理系统后台</span>
        </router-link>
      </a-col>
      <a-col :span="5" :offset="13" >
        <div style="float: right; padding-right: 30px">
          <span>
            <h3 style="color: #fff">欢迎您 ;
            <span style="color: #fff;font-weight: bolder">{{ user.loginName }}</span> &nbsp;&nbsp;
              <a-popconfirm
                  title="确定退出吗?"
                  ok-text="确认"
                  cancel-text="取消"
                  @confirm="logout()"
                  widt
              >
               <a style="color: #fff"> 退出</a>
            </a-popconfirm>
            </h3></span>
        </div>
      </a-col>
    </a-row>
  </a-layout-header>
</template>

<script lang="ts">
import {defineComponent, computed, ref} from 'vue';
import store from "@/store";
import axios from "axios";
import {message} from "ant-design-vue";
import router from "@/router";


export default defineComponent({
  name: "the-header",
  components: {},
  setup() {

    const user = computed(() => store.state.user);
    const logout = () => {
      axios.get('/logout').then((response) => {
        const data = response.data;
        if (data.code == 200) {
          message.success("退出登录成功！");
          setTimeout(function () {
            store.commit("setUser", {});
            router.push('/')
          }, 1000)
        } else {
          message.error(data.message);
        }
      });
    }

    return {
      user,
      logout
    };
  },
});
</script>

<style>
.header {
  width: 100%;
  height: 40px !important;
  padding: 0 30px 0px 10px !important;
  line-height: 40px !important;
  background-color: cornflowerblue !important;
}
</style>