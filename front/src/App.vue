<template>
  <TheLogin v-show="!user.loginName"></TheLogin>
  <div v-show="user.loginName" style="width: 100%; height: 100%">
    <a-layout style="width: 100%; height: 100%">
      <TheHeader></TheHeader>
      <router-view/>
      <TheFooter></TheFooter>
    </a-layout>
  </div>
</template>

<script lang="ts">
import {computed, defineComponent, ref} from 'vue';
import TheLogin from "@/components/the-login.vue";
import TheHeader from "@/components/the-header.vue";
import TheFooter from "@/components/the-footer.vue";
import store from "@/store";
import axios from "axios";

export default defineComponent({
  name: 'app',
  components: {
    TheLogin,
    TheHeader,
    TheFooter
  },
  setup() {

    const user = computed(() => store.state.user);

    const testData = ref();

    return {
      user,
      testData
    }
  }
});
</script>

