<template>
  <form>
    <div>
      <h1 class="font-bold text-green-500">Connexion</h1>
      <label><b>Username</b></label>
      <input type="text" placeholder="Enter your username here" v-model="username" required>
      <label><b>Password</b></label>
      <input type="password" placeholder="Enter your password here" v-model="password" required>
      <input  type="submit" value="Sumbit">
    </div>
  </form>
</template>
<script>

export default {

  name: "Connexion-component",

  data: () => ({
    username: "",
    password: ""
  })
}

</script>

<style>

</style>
