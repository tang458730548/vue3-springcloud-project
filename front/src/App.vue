<template>
  <TheLogin></TheLogin>
</template>

<script lang="ts">
import {defineComponent} from 'vue';
import TheLogin from "@/components/the-login.vue";

export default defineComponent({
  name: 'app',
  components: {
    TheLogin
  },
});
</script>

