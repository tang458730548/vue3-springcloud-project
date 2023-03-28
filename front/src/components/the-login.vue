<template>
  <div class="container">
    <div class="container-login">
      <a-card :bordered="false">
        <template v-slot:title>
          <h3 class="container-login-title">登陆</h3>
        </template>
        <a-row type="flex">
          <a-col flex="280px">
            <a-image style="height: 280px"
                     src="https://zos.alipayobjects.com/rmsportal/jkjgkEfvpUPVyRjUImniVslZfWPnJuuZ.png"
            />
          </a-col>
          <a-col flex="auto">
            <a-card title="" :bordered="false" style="width: 100% ; height:100%">
              <a-form
                  :model="formState"
                  name="normal_login"
                  class="login-form"
                  @finish="onFinish"
                  @finishFailed="onFinishFailed"
                  v-bind="layout"
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

                <a-form-item>
                  <a-form-item name="remember" no-style>
                    <a-checkbox v-model:checked="formState.remember">记住我</a-checkbox>
                    <a class="login-form-forgot" href="">忘记密码？</a>
                  </a-form-item>
                </a-form-item>
                <a-form-item>
                  <a-button :disabled="disabled" size="large" type="primary" html-type="submit"
                            class="login-form-button">
                    登陆
                  </a-button> OR
                  <a href="">前往注册!</a>
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
import {defineComponent, reactive, computed} from 'vue';
import {UserOutlined, LockOutlined} from '@ant-design/icons-vue';

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
  },
  setup() {
    const formState = reactive<FormState>({
      loginName: '',
      password: '',
      remember: true,
    });

    const layout = {
      labelCol: {span: 4},
      wrapperCol: {span: 16},
    };

    const onFinish = (values: any) => {
      console.log('Success:', values);
    };

    const onFinishFailed = (errorInfo: any) => {
      console.log('Failed:', errorInfo);
    };
    const disabled = computed(() => {
      return !(formState.loginName && formState.password);
    });
    return {
      layout,
      formState,
      onFinish,
      onFinishFailed,
      disabled,
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
  padding: 80px 0px;
  background-color: #eef;
}

.container-login {
  width: 700px;
  height: 100%;
  margin: 0 auto;
  border: 1px solid transparent;
  /*background: linear-gradient(white, white) padding-box, repeating-linear-gradient(-45deg, black 0, black 25%, white 0, white 50%) 0/.6em .6em;*/
  animation: ants 12s linear infinite;
}

.container-login-title {
  text-align: center;
}
</style>