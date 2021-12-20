<template>
  <form class="mx-auto max-w-xs">
    <div class="flex flex-wrap -mx-3 mb-6">
      <div class="w-full px-3">
        <label class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2" for="username">
          Username
        </label>
        <input class="appearance-none block w-full bg-gray-200 text-gray-700 border border-red-500 rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white" id="username" type="text" placeholder="Username">
        <p class="text-red-500 text-xs italic">Please fill out this field.</p>
      </div>
    </div>
    <div class="flex flex-wrap -mx-3 mb-6">
      <div class="w-full px-3">
        <label class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2" for="password">
          Password
        </label>
        <input class="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white focus:border-gray-500" id="password" type="password" placeholder="Password">
        <p class="text-gray-600 text-xs italic">Mettre une règle pour les mdp ici (peut-etre utf-8)</p>
      </div>
    </div>
    <div>
      <button class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:shadow-outline" type="button">
        Register
      </button>
    </div>
  </form>
</template>

<script>
export default {
  name: "CreateUserComponent",
  data: () => ({
    username: '',
    password: ''
  })
}
</script>