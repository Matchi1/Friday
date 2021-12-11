<template>
  <h1>{{ msg }}</h1>
</template>

<script>
export default {
  name: 'DashBoard',
  data() {
    return {
      msg: ''
    }
  },
  mounted() {
    fetch("/event/all")
        .then((response) => response.text())
        .then((data) => {
          this.msg = data;
        });
  }
}
</script>