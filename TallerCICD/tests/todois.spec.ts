import { test, expect } from '@playwright/test';
import { LoginPage } from '../pages/LoginPage';
import { TasksPage } from '../pages/TaskPage';

let loginPage: LoginPage;
let tasksPage: TasksPage;

// Configuración de BeforeEach y AfterEach
test.beforeEach(async ({ page }) => {
  loginPage = new LoginPage(page);
  tasksPage = new TasksPage(page);
  await page.goto('http://127.0.0.1:5000/');
  await loginPage.navigateToLoginPage();
  await loginPage.generateTestAccount();
  await page.waitForTimeout(1000);
  await loginPage.login();
});

test.afterEach(async ({ page }) => {
  await tasksPage.logout();
});

test('Adicionar una task y validar que la task es visible', async ({ page }) => {
  await tasksPage.addTask('Caso de prueba add task');
  await expect(tasksPage.getTask('Caso de prueba add task')).toBeVisible();
});

test('Adicionar una task, marcarla como completada y validar que está completada', async ({ page }) => {
  await tasksPage.addTask('Caso de prueba marcar como completada');
  await tasksPage.markTaskAsCompleted('Caso de prueba marcar como completada');
  await expect(tasksPage.getCompletedTask('Caso de prueba marcar como completada')).toBeVisible();
});

test('Adicionar una task, marcarla como completada, limpiar tareas y validar que no existe', async ({ page }) => {
  await tasksPage.addTask('Caso de prueba limpiar tarea');
  await tasksPage.markTaskAsCompleted('Caso de prueba limpiar tarea');
  await tasksPage.clearTasks();
  await expect(tasksPage.getTask('Caso de prueba limpiar tarea')).not.toBeVisible();
});
