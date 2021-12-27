<template>
  <div class="mx-auto max-w-xs">
    <form class="bg-white shadow-xl rounded px-8 pt-6 pb-8 mb-4">
      <div class="mb-4">
        <label class="block text-gray-700 text-sm font-bold mb-2" for="username">Username</label>
        <input class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" id="username" type="text" placeholder="Username" v-model="username" required>
      </div>
      <div class="mb-6">
        <label class="block text-gray-700 text-sm font-bold mb-2" for="password">Password</label>
        <input class="shadow appearance-none border border-red-500 rounded w-full py-2 px-3 text-gray-700 mb-3 leading-tight focus:outline-none focus:shadow-outline" id="password" type="password" placeholder="Password"  v-model="password" required>
        <p class="text-red-500 text-xs italic">Please choose a password.</p>
      </div>
      <div class="flex items-center justify-between">
        <button class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:shadow-outline" type="button" @click="connexion(); getUsername();">
          Sign In
        </button>
        <a class="inline-block align-baseline font-bold text-sm text-blue-500 hover:text-blue-800">
          <router-link to="/createUser">New user ?</router-link>
        </a>
      </div>
      <div v-if="wrongUser" class="pt-1.5 text-red-500">Username or password not matching</div>
    </form>
  </div>
</template>
<script>

import router from "@/router";

export default {

  name: "ConnexionComponent",

  data: () => ({
    username: "",
    password: "",
    wrongUser: false,
  }),

  methods: {
    connexion() {
      fetch("/user/exist",
          {
            method: 'POST',
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify({username: this.username, password: this.password})
          }).then(function (res) {
          if (res.status === 200) {
            router.push("/dashboard")
          }
      })
      this.wrongUser = true
    },
    getUsername() {
      const username = JSON.stringify(this.username);
      localStorage.setItem('user', username)
    }
  }
}

</script>

<style>

</style>
