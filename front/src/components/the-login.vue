<template>
  <div class="container">
    <div class="container-login">
      <a-card :bordered="false">
        <a class="trans" @click="exchangeScan()">
          <QrcodeOutlined>
          </QrcodeOutlined>
          <div class="imgApp"></div>
        </a>
        <template v-slot:title>
          <h2 class="container-login-title">登陆</h2>
        </template>
        <a-row type="flex" style="padding-top: 0;height: 300px">
          <a-col flex="280px">
            <a-image style="height: 280px"
                     src="https://zos.alipayobjects.com/rmsportal/jkjgkEfvpUPVyRjUImniVslZfWPnJuuZ.png"
            />
          </a-col>
          <a-col flex="auto">
            <a-card title="" :bordered="false" style="width: 100% ; height:100%;">
              <a-form
                  :loading="formLoading"
                  :model="formState"
                  name="normal_login"
                  class="login-form"
                  @finish="onFinish"
                  @finishFailed="onFinishFailed"
                  v-bind="layout"
                  v-show="!isShowScan"
              >
                <a-form-item
                    label="用户名"
                    name="loginName"
                    :rules="[{ required: true, message: '请输入用户名!' }]"
                >
                  <a-input v-model:value="formState.loginName">
                    <template #prefix>
                      <UserOutlined class="site-form-item-icon"/>
                    </template>
                  </a-input>
                </a-form-item>

                <a-form-item
                    label="密码"
                    name="password"
                    :rules="[{ required: true, message: '请输入密码!' }]"
                >
                  <a-input-password v-model:value="formState.password">
                    <template #prefix>
                      <LockOutlined class="site-form-item-icon"/>
                    </template>
                  </a-input-password>
                </a-form-item>

                <a-form-item name="remember" style="text-align: center">
                  <a-checkbox v-model:checked="formState.remember">记住我</a-checkbox>
                  <a class="login-form-forgot" href="">&nbsp;忘记密码？</a>
                </a-form-item>

                <a-form-item style="text-align: center" :wrapper-col="{ span: 22, offset: 2 }">
                  <a-button :disabled="disabled" type="primary" html-type="submit"
                            style="width: 60%; text-align: center"
                            :loading="formLoading"
                            class="login-form-button">
                    登陆
                  </a-button>
                  <a class="login-form-forgot" style="margin-left: 10px" href="">&nbsp;前往注册？</a>
                </a-form-item>

                <div style="text-align: center">
                  <a>
                    <WechatOutlined class="wechart-svg"/>
                    <span style="vertical-align: middle">&nbsp;微信登陆</span>
                  </a>
                  <a>
                    <QqOutlined class="qq-svg"/>
                    <span style="vertical-align: middle">&nbsp;qq登陆</span>
                  </a>
                </div>
              </a-form>
              <a-form
                  :model="formState"
                  name="normal_login"
                  class="login-form"
                  @finish="onFinish"
                  @finishFailed="onFinishFailed"
                  v-bind="layout"
                  v-show="isShowScan"
              >
                <a-form-item :wrapper-col="{ span: 20, offset: 4  }">
                  <QrcodeOutlined class="scan-svg">
                  </QrcodeOutlined>
                </a-form-item>
              </a-form>
            </a-card>
          </a-col>
        </a-row>
      </a-card>
    </div>
  </div>

</template>

<script lang="ts">
import {defineComponent, reactive, computed, ref} from 'vue';
import {UserOutlined, LockOutlined, WechatOutlined, QqOutlined, QrcodeOutlined} from '@ant-design/icons-vue';
import axios from 'axios'
import {message} from "ant-design-vue";
import store from "@/store";

interface FormState {
  loginName: string;
  password: string;
  remember: boolean;
}

export default defineComponent({
  name: "the-login",
  components: {
    UserOutlined,
    LockOutlined,
    WechatOutlined,
    QqOutlined,
    QrcodeOutlined
  },
  setup() {
    const isShowScan = ref(false)

    const formLoading = ref(false)

    const formState = reactive<FormState>({
      loginName: '',
      password: '',
      remember: true,
    });

    const layout = {
      labelCol: {span: 5},
      wrapperCol: {span: 19},
    };

    const onFinish = (values: any) => {
      console.log(values)
      formLoading.value = true
      axios.post('login', {
        ...values
      }).then(response => {
        if (response.data.code == 200) {
          console.log("登陆成功");
          message.success(`登陆成功！`)
          setTimeout(function () {
            store.commit("setUser", response.data.data);
          }, 1000)
        }else{
          message.error(`用户名或者密码错误！`)
        }
        setTimeout(function () {
          formLoading.value = false
        }, 1000)
      })
    };

    const onFinishFailed = (errorInfo: any) => {
      console.log('Failed:', errorInfo);
    };
    const disabled = computed(() => {
      return !(formState.loginName && formState.password);
    });
    const exchangeScan = () => {
      isShowScan.value = !isShowScan.value
    }

    return {
      layout,
      formState,
      onFinish,
      onFinishFailed,
      disabled,
      exchangeScan,
      isShowScan,
      formLoading
    };
  },
});
</script>

<style>
* {
  margin: 0;
  padding: 0;
}

.container {
  height: 100%;
  width: 100%;
  /*background-color: black;*/
  padding: 150px 0;
  background-color: cornflowerblue;
}

.container-login {
  width: 680px;
  height: 100%;
  margin: 0 auto;
  border: 1px solid transparent;
  /*background: linear-gradient(white, white) padding-box, repeating-linear-gradient(-45deg, black 0, black 25%, white 0, white 50%) 0/.6em .6em;*/
  animation: ants 12s linear infinite;
}

.container-login-title {
  text-align: center;
  height: 20px;
  line-height: 30px;
}

.wechart-svg svg, .qq-svg svg {
  width: 25px;
  height: 25px;
  margin-left: 10px;
  vertical-align: middle;
}

.trans svg {
  position: absolute;
  right: 0;
  width: 64px;
  height: 64px;
  /*background-color: #2b96e6;*/
  top: 0;
}

.imgApp {
  width: 64px;
  height: 64px;
  position: absolute;
  right: 0;
  top: 0;
  background: linear-gradient(225deg, transparent 45px, #fff 0);
}

.container-login .ant-card {
  border-radius: 10px;
}

.container-login .ant-card-body {
  padding: 15px;
}

.scan-svg svg {
  height: 250px;
  line-height: 250px;
  width: 250px;
  margin: 0 auto;
  vertical-align: middle;
}

</style>