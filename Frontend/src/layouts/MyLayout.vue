<template>
  <q-layout view="lhh lpr lFf" >
    <q-header elevated>
        <q-toolbar class="bg-indigo-9">
        <q-btn
          flat
          dense
          round
          @click="leftDrawerOpen = !leftDrawerOpen"
          icon="menu"
          aria-label="Menu"
        />

        <q-toolbar-title >
          Admin Ini Punya Akses
        </q-toolbar-title>

        <div class="text-white">Admin v{{ $q.version }}</div>
      </q-toolbar>
    </q-header>

    <q-drawer
      v-model="leftDrawerOpen"
      show-if-above
      bordered
      content-class="bg-deep-orange-9"
    >
      <q-list class="text-white">
        <q-item-label
        header
        class="text-center bg-grey-1 text-black text-h6"
        >Pilih Menu</q-item-label>
        <q-item clickable tag="a" href="/index">
          <q-item-section avatar>
            <q-icon name="home"/>
          </q-item-section>
          <q-item-section >
            <q-item-label>DASHBOARD</q-item-label>
          </q-item-section>
        </q-item>

          <q-item-section>
            <q-btn-dropdown
            icon="person"
            class="q-mx-lg"
            flat=""
            align="center"
            label="LECTURER"
            >
              <q-list>
                <q-item clickable tag="a" href="/inputdosen">
                  <q-item-section>
                    <q-item-label>Input Lecturer</q-item-label>
                  </q-item-section>
                </q-item>

                <q-item clickable tag="a" href="/lihatdosen">
                  <q-item-section>
                    <q-item-label>Status Lecturer</q-item-label>
                  </q-item-section>
                </q-item>
              </q-list>
            </q-btn-dropdown>
            <q-btn-dropdown
            icon="people"
            class="q-mx-lg"
            flat
            align="center"
            label="STUDENT"
            >
              <q-list>
                <q-item clickable tag="a" href="/inputmhs">
                  <q-item-section>
                    <q-item-label>Input Student</q-item-label>
                  </q-item-section>
                </q-item>

                <q-item clickable tag="a" href="/lihatmhs">
                  <q-item-section>
                    <q-item-label>Status Student</q-item-label>
                  </q-item-section>
                </q-item>
              </q-list>
            </q-btn-dropdown>
            <q-btn-dropdown
            icon="money"
            class="q-mx-lg"
            flat
            align="center"
            label="FINANCE"
            >
              <q-list>
                <q-item clickable tag="a" href="/inputuang">
                  <q-item-section>
                    <q-item-label>Input Financial</q-item-label>
                  </q-item-section>
                </q-item>

                <q-item clickable tag="a" href="/lihatuang">
                  <q-item-section>
                    <q-item-label>Status Financial</q-item-label>
                  </q-item-section>
                </q-item>
              </q-list>
            </q-btn-dropdown>
            <q-btn-dropdown
            icon="school"
            class="q-mx-lg"
            flat
            align="center"
            label="COURSES"
            >
              <q-list>
                <q-item clickable tag="a" href="/inputmatkul">
                  <q-item-section>
                    <q-item-label>Input Courses</q-item-label>
                  </q-item-section>
                </q-item>

                <q-item clickable tag="a" href="/lihatmatkul">
                  <q-item-section>
                    <q-item-label>Status Courses</q-item-label>
                  </q-item-section>
                </q-item>
              </q-list>
            </q-btn-dropdown>
          </q-item-section>

        <q-item clickable tag="a" href="/">
          <q-item-section avatar>
            <q-icon name="logout" />
          </q-item-section>
          <q-item-section>
            <q-item-label>LOGOUT</q-item-label>
            <!-- <q-item-label caption class="text-indigo">Keluar lah sebelum di keluarkan</q-item-label> -->
          </q-item-section>
        </q-item>
      </q-list>
    </q-drawer>

    <q-page-container>
      <router-view />
    </q-page-container>
  </q-layout>
</template>

<script>
export default {
  name: 'MyLayout',

  data () {
    return {
      leftDrawerOpen: false
    }
  }
}
</script>
